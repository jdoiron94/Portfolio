#include <cuda_runtime.h>

#include <stdio.h>

// Completed by Jacob Doiron and Andrew Riehl

#define imin(a, b)  (a < b ? a : b)

const int n = 5;
const int threadsPerBlock = 256;
const int blocksPerGrid = imin(32, (n + threadsPerBlock - 1) / threadsPerBlock);


// Takes in three vectors a,b,c and adds a and b into c
__global__ void vectorAdd(const int *a, const int *b, int *c, const int numElements)
{
  // Used to hold results, until they can be added into c
    __shared__ int cache[threadsPerBlock];
    int index = (blockIdx.x * blockDim.x) + threadIdx.x;
    int cacheIndex = threadIdx.x;
    int temp = 0;
    while (index < numElements) {
        temp += a[index] + b[index];
        index += blockDim.x * gridDim.x;
    }
    cache[cacheIndex] = temp;
  // Called to make sure that all threads are synced
  // All threads running have to call this before continuing on with the code
    __syncthreads();
    if (cacheIndex == 0) {
        c[blockIdx.x] = cache[0];
    }
}

int main()
{
      // Error code to check return values for CUDA calls
      cudaError_t err = cudaSuccess;

      // Print the vector length to be used, and compute its size
      size_t size = n * sizeof(int);
      printf("[Vector addition of %d elements]\n", n);

      // Allocate the host input vector A
      int *h_A = (int*) malloc(size);

      // Allocate the host input vector B
      int *h_B = (int*) malloc(size);

      // Allocate the host output vector C
      int *h_C = (int*) malloc(size);

      // Verify that allocations succeeded
      if (h_A == NULL || h_B == NULL || h_C == NULL) {
          fprintf(stderr, "Failed to allocate host vectors!\n");
          exit(EXIT_FAILURE);
      }

      // Initialize the host input vectors
      for (int i = 0; i < n; i++) {
          h_A[i] = rand() / 100000;
          h_B[i] = rand() / 100000;
          printf("h1[%d]: %5d, h2[%d]: %5d\n", i, h_A[i], i, h_B[i]);
      }

      // Allocate the device input vector A
      int *d_A = NULL;
      err = cudaMalloc((void**) &d_A, size);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to allocate device vector A (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Allocate the device input vector B
      int *d_B = NULL;
      err = cudaMalloc((void**) &d_B, size);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to allocate device vector B (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Allocate the device output vector C
      int *d_C = NULL;
      err = cudaMalloc((void**) &d_C, size);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to allocate device vector C (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Copy the host input vectors A and B in host memory to the device input vectors in
      // device memory
      printf("Copy input data from the host memory to the CUDA device\n");
      err = cudaMemcpy(d_A, h_A, size, cudaMemcpyHostToDevice);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to copy vector A from host to device (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      err = cudaMemcpy(d_B, h_B, size, cudaMemcpyHostToDevice);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to copy vector B from host to device (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Launch the Vector Add CUDA Kernel
      printf("CUDA kernel launch with %d blocks of %d threads\n", blocksPerGrid, threadsPerBlock);
      vectorAdd<<<blocksPerGrid, threadsPerBlock>>>(d_A, d_B, d_C, n);
      err = cudaGetLastError();

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to launch vectorAdd kernel (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Copy the device result vector in device memory to the host result vector
      // in host memory.
      printf("Copy output data from the CUDA device to the host memory\n");
      err = cudaMemcpy(h_C, d_C, size, cudaMemcpyDeviceToHost);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to copy vector C from device to host (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      for (int i = 0; i < n; i++) {
          printf("h3[%d]: %5d\n", i, h_C[i]);
      }

      // Free device global memory
      err = cudaFree(d_A);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to free device vector A (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      err = cudaFree(d_B);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to free device vector B (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      err = cudaFree(d_C);

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to free device vector C (error code %s)!\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      // Free host memory
      free(h_A);
      free(h_B);
      free(h_C);

      // Reset the device and exit
      // cudaDeviceReset causes the driver to clean up all state. While
      // not mandatory in normal operation, it is good practice.  It is also
      // needed to ensure correct operation when the application is being
      // profiled. Calling cudaDeviceReset causes all profile data to be
      // flushed before the application exits
      err = cudaDeviceReset();

      if (err != cudaSuccess) {
          fprintf(stderr, "Failed to deinitialize the device! error=%s\n", cudaGetErrorString(err));
          exit(EXIT_FAILURE);
      }

      printf("Done\n");
      return 0;
}
