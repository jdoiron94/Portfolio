// lab6.cu
// Completed by Jacob Doiron and Andrew Riehl
// COSC 480: GPU Programming
// 10/30/15
// Project which demonstrates the addition of two vectors, using multiple streams

// Important to note results will result in a loss of precision due to using
// floating point numbers instead of doubles (twice the precision, twice the memory).

#include <stdlib.h>
#include <time.h>

#include <cuda_runtime.h>
#include "./common/book.h"

#define N   (1024 * 1024)
#define FULL_DATA_SIZE   (N * 20)

// Takes in three vectors a, b, and c, and adds vectors a and b into vector c
__global__ void vectorAdd(const float *a, const float *b, float *c)
{
  int i = blockDim.x * blockIdx.x + threadIdx.x;
  if (i < N) {
    float result = a[i] + b[i];
    c[i] = result;
  }
}

// Method which generates a random float value up to the specified max
float randomFloat(int max)
{
  int random = rand() % max;
  float scalar = (float) rand() / RAND_MAX;
  float result = random * scalar;
  return result;
}

// Main method which runs everything
int main(void)
{

  int whichDevice;
  float elapsed;

  cudaDeviceProp prop;
  cudaEvent_t start;
  cudaEvent_t stop;
  cudaStream_t stream0;
  cudaStream_t stream1;
  cudaStream_t stream2;
  cudaStream_t stream3;

  // Create the timers
  HANDLE_ERROR(cudaEventCreate(&start));
  HANDLE_ERROR(cudaEventCreate(&stop));

  // Initialize the streams
  HANDLE_ERROR(cudaStreamCreate(&stream0));
  HANDLE_ERROR(cudaStreamCreate(&stream1));
  HANDLE_ERROR(cudaStreamCreate(&stream2));
  HANDLE_ERROR(cudaStreamCreate(&stream3));

  float *host_a, *host_b, *host_c;

  float *device_a0, *device_b0, *device_c0;
  float *device_a1, *device_b1, *device_c1;
  float *device_a2, *device_b2, *device_c2;
  float *device_a3, *device_b3, *device_c3;

  HANDLE_ERROR(cudaGetDevice(&whichDevice));
  HANDLE_ERROR(cudaGetDeviceProperties(&prop, whichDevice));

  if (!prop.deviceOverlap) {
    printf("Device will not handle overlaps, so no speed up from streams\n");
    return 0;
  }

  // Seed the random function to get random values from #rand
  srand(time(NULL));

  printf("Performing test on vectors, each with size %d (elements)\n", FULL_DATA_SIZE);

  // Allocate GPU memory
  HANDLE_ERROR(cudaMalloc((void**) &device_a0, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_b0, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_c0, N * sizeof(float)));

  HANDLE_ERROR(cudaMalloc((void**) &device_a1, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_b1, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_c1, N * sizeof(float)));

  HANDLE_ERROR(cudaMalloc((void**) &device_a2, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_b2, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_c2, N * sizeof(float)));

  HANDLE_ERROR(cudaMalloc((void**) &device_a3, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_b3, N * sizeof(float)));
  HANDLE_ERROR(cudaMalloc((void**) &device_c3, N * sizeof(float)));

  // Allocate host locked memory for streams
  HANDLE_ERROR(cudaHostAlloc((void**) &host_a, FULL_DATA_SIZE * sizeof(float), cudaHostAllocDefault));
  HANDLE_ERROR(cudaHostAlloc((void**) &host_b, FULL_DATA_SIZE * sizeof(float), cudaHostAllocDefault));
  HANDLE_ERROR(cudaHostAlloc((void**) &host_c, FULL_DATA_SIZE * sizeof(float), cudaHostAllocDefault));

  // Populate host vectors
  for (int i = 0; i < FULL_DATA_SIZE; i++) {
    host_a[i] = randomFloat(100);
    host_b[i] = randomFloat(100);
  }

  // Record the start time
  HANDLE_ERROR(cudaEventRecord(start, 0));

  for (int i = 0; i < FULL_DATA_SIZE; i += N * 4) {
    // Enqueue a into the streams
    HANDLE_ERROR(cudaMemcpyAsync(device_a0, host_a + i, N * sizeof(float), cudaMemcpyHostToDevice, stream0));
    HANDLE_ERROR(cudaMemcpyAsync(device_a1, host_a + i, N * sizeof(float), cudaMemcpyHostToDevice, stream1));
    HANDLE_ERROR(cudaMemcpyAsync(device_a2, host_a + i, N * sizeof(float), cudaMemcpyHostToDevice, stream2));
    HANDLE_ERROR(cudaMemcpyAsync(device_a3, host_a + i, N * sizeof(float), cudaMemcpyHostToDevice, stream3));

    // Enqueue b into the streams
    HANDLE_ERROR(cudaMemcpyAsync(device_b0, host_b + i, N * sizeof(float), cudaMemcpyHostToDevice, stream0));
    HANDLE_ERROR(cudaMemcpyAsync(device_b1, host_b + i, N * sizeof(float), cudaMemcpyHostToDevice, stream1));
    HANDLE_ERROR(cudaMemcpyAsync(device_b2, host_b + i, N * sizeof(float), cudaMemcpyHostToDevice, stream2));
    HANDLE_ERROR(cudaMemcpyAsync(device_b3, host_b + i, N * sizeof(float), cudaMemcpyHostToDevice, stream3));

    // Enqueue the kernel into the streams
    vectorAdd<<<N / 256, 256, 0, stream0>>>(device_a0, device_b0, device_c0);
    vectorAdd<<<N / 256, 256, 0, stream1>>>(device_a1, device_b1, device_c1);
    vectorAdd<<<N / 256, 256, 0, stream2>>>(device_a2, device_b2, device_c2);
    vectorAdd<<<N / 256, 256, 0, stream3>>>(device_a3, device_b3, device_c3);

    // Enqueue c from device to locked memory
    HANDLE_ERROR(cudaMemcpyAsync(host_c + i, device_c0, N * sizeof(float), cudaMemcpyDeviceToHost, stream0));
    HANDLE_ERROR(cudaMemcpyAsync(host_c + i + N, device_c1, N * sizeof(float), cudaMemcpyDeviceToHost, stream1));
    HANDLE_ERROR(cudaMemcpyAsync(host_c + i + (N * 2), device_c2, N * sizeof(float), cudaMemcpyDeviceToHost, stream2));
    HANDLE_ERROR(cudaMemcpyAsync(host_c + i + (N * 3), device_c3, N * sizeof(float), cudaMemcpyDeviceToHost, stream3));
  }

  // Synchronizes all streams
  HANDLE_ERROR(cudaStreamSynchronize(stream0));
  HANDLE_ERROR(cudaStreamSynchronize(stream1));
  HANDLE_ERROR(cudaStreamSynchronize(stream2));
  HANDLE_ERROR(cudaStreamSynchronize(stream3));

  // Record the stop time
  HANDLE_ERROR(cudaEventRecord(stop, 0));

  // Synchronize the stop timer
  HANDLE_ERROR(cudaEventSynchronize(stop));

  // Record the elapsed time between the timers
  HANDLE_ERROR(cudaEventElapsedTime(&elapsed, start, stop));

  printf("Time taken: %3.1f ms\nVALIDATION:\n", elapsed);

  // Test a small subset of the results
  for (int i = 0; i < 10; i++) {
    printf("a[%d]: %9.6f, b[%d]: %9.6f, c[%d]: %9.6f\n", i, host_a[i], i, host_b[i], i, host_c[i]);
  }

  // Frees all device memory
  HANDLE_ERROR(cudaFree(device_a0));
  HANDLE_ERROR(cudaFree(device_b0));
  HANDLE_ERROR(cudaFree(device_c0));

  HANDLE_ERROR(cudaFree(device_a1));
  HANDLE_ERROR(cudaFree(device_b1));
  HANDLE_ERROR(cudaFree(device_c1));

  HANDLE_ERROR(cudaFree(device_a2));
  HANDLE_ERROR(cudaFree(device_b2));
  HANDLE_ERROR(cudaFree(device_c2));

  HANDLE_ERROR(cudaFree(device_a3));
  HANDLE_ERROR(cudaFree(device_b3));
  HANDLE_ERROR(cudaFree(device_c3));

  printf("All device memory has been freed.\n");

  // Frees all host memory
  HANDLE_ERROR(cudaFreeHost(host_a));
  HANDLE_ERROR(cudaFreeHost(host_b));
  HANDLE_ERROR(cudaFreeHost(host_c));

  printf("All host memory has been freed.\n");

  // Destroys all streams
  HANDLE_ERROR(cudaStreamDestroy(stream0));
  HANDLE_ERROR(cudaStreamDestroy(stream1));
  HANDLE_ERROR(cudaStreamDestroy(stream2));
  HANDLE_ERROR(cudaStreamDestroy(stream3));

  printf("All streams have been destroyed.\n");

  // Destroys timer events
  HANDLE_ERROR(cudaEventDestroy(start));
  HANDLE_ERROR(cudaEventDestroy(stop));

  printf("All timers have been destroyed.\n");

  return 0;
}
