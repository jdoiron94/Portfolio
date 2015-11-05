// proj2.cu
// Merle Ferguson and Jacob Doiron
// COSC 480: GPU Programming
// 10/16/15
// Project which demonstrates parallel matrix addition, using shared memory


// We experienced the atomicAdd kernel taking longer (about twice as long)
// as the regular add. We believe this is because, despite atomic additions
// being very quick, only one happens at a time. Performing a bunch of
// slow adds at the same time in our case was quicker than performing
// a bunch of quick adds sequentially.

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <cuda_runtime.h>

#define threadsPerBlock 256

// Accepts a 2D array as a parameter and flattens it into a 1D array
void flattenHostMatrix(int **h_a, int *r_a, const int rows, const int columns)
{
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      r_a[j + i * columns] = h_a[i][j];
    }
  }
}

// Accepts a 1D array as a parameter and expands it into a 2D array
void expandHostMatrix(int **h_c, const int *r_c, const int rows, const int columns)
{
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      h_c[i][j] = r_c[j + i * columns];
    }
  }
}

// Mallocs the 2D host array and returns it
int **createHostMatrix(const int rows, const int columns)
{
  int **host = (int**) malloc(rows * sizeof(int*));
  if (host == NULL) {
    printf("Failed to allocate host matrix!\n");
    exit(EXIT_FAILURE);
  }
  for (int i = 0; i < rows; i++) {
    host[i] = (int*) malloc(columns * sizeof(int));
    if (host[i] == NULL) {
      printf("Failed to allocate host matrix!\n");
      exit(EXIT_FAILURE);
    }
  }
  return host;
}

// Fills the random values of the 2D host arrays
int **initializeHostMatrix(const int rows, const int columns)
{
  int **host = createHostMatrix(rows, columns);
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      host[i][j] = rand() % 100;
    }
  }
  return host;
}

// Frees the 2D host arrays
void freeHostMatrix(int **host, const int rows)
{
  for (int i = 0; i < rows; i++) {
    free(host[i]);
  }
  free(host);
}

// Mallocs the device arrays
int *createDeviceMatrix(cudaError_t err, const int rows, const int columns)
{
  int *device = NULL;
  err = cudaMalloc((void**) &device, rows * columns * sizeof(int));
  if (err != cudaSuccess) {
    printf("Failed to allocate device matrix (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  return device;
}

// Initializes the device array with the provided (flattened) host array
int *initializeDeviceMatrix(cudaError_t err, const int *host, const int rows, const int columns)
{
  int *device = createDeviceMatrix(err, rows, columns);
  err = cudaMemcpy(device, host, rows * columns * sizeof(int), cudaMemcpyHostToDevice);
  if (err != cudaSuccess) {
    printf("Failed to copy vector A from host to device (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  return device;
}

// Frees the device arrays
void freeDeviceMatrix(cudaError_t err, int *device)
{
  err = cudaFree(device);
  if (err != cudaSuccess)  {
    printf("Failed to free device matrix (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
}

// Sums two matrices in 1D array form and stores the result in shared memory.
// Then copies this result from shared memory to the array that is used later
__global__ void addMatrices(const int *a, const int *b, int *c, const int n, const int m)
{
  __shared__ int cache[threadsPerBlock];

  const unsigned int i = threadIdx.x;
  unsigned int tid = blockDim.x * blockIdx.x + i;
  const unsigned int stride = blockDim.x * gridDim.x;

  while (tid < n * m) {
    // Store the sum for all threads in the block
    cache[i] = a[tid] + b[tid];
    __syncthreads();
    // Copy the sums from the cache to the return array
    c[tid] = cache[i];
    __syncthreads();
    // Skip the total number of threads in the grid so this thread can work on a new unique element
    tid += stride;
  }
}

// Sums two matrices in 1d array form and stores the result in shared memory. Then copies this result from shared memory to the array that is used later
// In particular, this uses the atomicAdd for additions
__global__ void addMatricesAtomically(const int *a, const int *b, int *c, const int n, const int m)
{
  __shared__ int cache[threadsPerBlock];

  const unsigned int i = threadIdx.x;
  unsigned int tid = blockDim.x * blockIdx.x + i;
  const unsigned int stride = blockDim.x * gridDim.x;

  while (tid < n * m) {
    // Store the sum for all threads in the block
    atomicAdd(&cache[i], a[i]);
    atomicAdd(&cache[i], b[i]);
    // Copy the sums from the cache to the return array
    c[tid] = cache[i];
    __syncthreads();
    // Skip the total number of threads in the grid so this thread can work on a new unique element
    tid += stride;
  }
}

// Calls functions to sum matrices in two different ways
void callKernel(cudaError_t err, const int *device_a, const int *device_b, int *device_c, int *host_c, const int rows, const int columns, const int atomic)
{
  int blocksPerGrid = ((rows * columns) + threadsPerBlock - 1) / threadsPerBlock;
  if (atomic) {
    addMatricesAtomically<<<blocksPerGrid, threadsPerBlock>>>(device_a, device_b, device_c, rows, columns);
  } else {
    addMatrices<<<blocksPerGrid, threadsPerBlock>>>(device_a, device_b, device_c, rows, columns);
  }
  err = cudaGetLastError();
  if (err != cudaSuccess) {
    printf("Failed to launch addMatrices kernel (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaMemcpy(host_c, device_c, (rows * columns*sizeof(int)), cudaMemcpyDeviceToHost);
  if (err != cudaSuccess) {
    printf("Failed to copy result matrix from device to host (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
}

// Calls functions to sum matrices in two different ways, recording the time each takes
int main()
{
  int rows;
  int columns;
  float elapsedTime;

  cudaEvent_t start;
  cudaEvent_t stop;
  cudaError_t err = cudaSuccess;

  printf("Please enter the number of rows per matrix: ");
  scanf("%d", &rows);
  printf("Please enter the number of columns per matrix: ");
  scanf("%d", &columns);

  srand(time(NULL));

  int **host_a = initializeHostMatrix(rows, columns);
  int **host_b = initializeHostMatrix(rows, columns);
  int **host_c = createHostMatrix(rows, columns);

  int *host_a_flat = (int*) malloc(rows * columns * sizeof(int));
  int *host_b_flat = (int*) malloc(rows * columns * sizeof(int));
  int *host_c_flat = (int*) malloc(rows * columns * sizeof(int));

  // Convert 2D matrices to 1D arrays so they can be copied into device memory
  flattenHostMatrix(host_a, host_a_flat, rows, columns);
  flattenHostMatrix(host_b, host_b_flat, rows, columns);

  int *device_a = initializeDeviceMatrix(err, host_a_flat, rows, columns);
  int *device_b = initializeDeviceMatrix(err, host_b_flat, rows, columns);
  int *device_c = createDeviceMatrix(err, rows, columns);

  err = cudaEventCreate(&start);
  if (err != cudaSuccess) {
    printf("Failed to create cuda event start (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventCreate(&stop);
  if (err != cudaSuccess) {
    printf("Failed to create cuda event stop (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventRecord(start, 0);
  if (err != cudaSuccess) {
    printf("Failed to record event start (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }

  callKernel(err, device_a, device_b, device_c, host_c_flat, rows, columns, 1);
  err = cudaEventRecord(stop, 0);
  if (err != cudaSuccess) {
    printf("Failed to record event stop (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventSynchronize(stop);
  if (err != cudaSuccess) {
    printf("Failed to synchronize (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventElapsedTime(&elapsedTime, start, stop);
  if (err != cudaSuccess) {
    printf("Failed to calculate elapsed time (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }

  printf("\nMatrix add for atomic %3.1f ms \n", elapsedTime);

  expandHostMatrix(host_c, host_c_flat, rows, columns);

  err = cudaEventCreate(&start);
  if (err != cudaSuccess) {
    printf("Failed to create cuda event start (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventCreate(&stop);
  if (err != cudaSuccess) {
    printf("Failed to create cuda event stop (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventRecord(start, 0);
  if (err != cudaSuccess) {
    printf("Failed to record event start (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }

  callKernel(err, device_a, device_b, device_c, host_c_flat, rows, columns, 0);
  err = cudaEventRecord(stop, 0);
  if (err != cudaSuccess) {
    printf("Failed to record event stop (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventSynchronize(stop);
  if (err != cudaSuccess) {
    printf("Failed to synchronize (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }
  err = cudaEventElapsedTime(&elapsedTime, start, stop);
  if (err != cudaSuccess) {
    printf("Failed to calculate elapsed time (error code %s)!\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }

  printf("\nMatrix add for non-atomic %3.1f ms \n", elapsedTime);

  expandHostMatrix(host_c, host_c_flat, rows, columns);

  printf("\nWriting results to results.txt . . .\n");

  // File pointer to record results
  FILE *file = fopen("results.txt", "w");

  if (file == NULL) {
    printf("Error opening file!\n");
    exit(1);
  }

  // Write original matrix to file
  fprintf(file, "Matrix A:\n");
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      fprintf(file, "%3d ", host_a[i][j]);
    }
    fprintf(file, "\n");
  }

  fprintf(file, "\n\n");

  // Write original matrix to file
  fprintf(file, "Matrix B:\n");
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      fprintf(file, "%3d ", host_b[i][j]);
    }
    fprintf(file, "\n");
  }

  fprintf(file, "\n\n");

  // Write resulting matrix to file
  fprintf(file, "Matrix Result:\n");
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
      fprintf(file, "%3d ", host_c[i][j]);
    }
    fprintf(file, "\n");
  }

  fprintf(file, "\n\n");
  fclose(file);

  printf("\nresults.txt is ready\n");

  freeDeviceMatrix(err, device_a);
  freeDeviceMatrix(err, device_b);
  freeDeviceMatrix(err, device_c);

  printf("\nDevice matrices freed\n");

  freeHostMatrix(host_a);
  freeHostMatrix(host_b);
  freeHostMatrix(host_c);

  free(host_a_flat);
  free(host_b_flat);
  free(host_c_flat);

  printf("\nHost matrices freed\n");

  err = cudaDeviceReset();
  if (err != cudaSuccess)  {
    printf("Failed to deinitialize the device! error=%s\n", cudaGetErrorString(err));
    exit(EXIT_FAILURE);
  }

  printf("Done\n");
  return 0;
}
