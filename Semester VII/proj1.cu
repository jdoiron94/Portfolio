#include <cuda_runtime.h>

#include <stdio.h>

// Completed by both Jacob Doiron and Andrew Riehl

__global__ void scaleArray(int *array, const int scalar, const int numElements)
{
	int index = blockDim.x * blockIdx.x + threadIdx.x;
	int stride = blockDim.x * gridDim.x;
	if (index < numElements) {
		array[index] *= scalar;
		index += stride;
	}
}

/**
* Host main routine
*/
int main()
{
	// Error code to check return values for CUDA calls
	cudaError_t err = cudaSuccess;

	printf("Number of elements in array: ");
	int numElements;
	scanf("%d", &numElements);
	size_t size = numElements * sizeof(int);

	printf("Scalar: ");
	int scalar;
	scanf("%d", &scalar);

	// Allocate the host input vector A
	int *h_A = (int*) malloc(size);

	// Verify that allocations succeeded
	if (h_A == NULL) {
		fprintf(stderr, "Failed to allocate host vectors!\n");
		exit(EXIT_FAILURE);
	}

	// Initialize the host input vector
	for (int i = 0; i < numElements; i++) {
		h_A[i] = rand() / 100000;
		printf("host[%d] = %d\n", i, h_A[i]);
	}

	// Allocate the device input vector A
	int *d_A = NULL;
	err = cudaMalloc((void**) &d_A, size);

	if (err != cudaSuccess) {
		fprintf(stderr, "Failed to allocate device vector A (error code %s)!\n", cudaGetErrorString(err));
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

	// Launch the Vector Add CUDA Kernel
	int threadsPerBlock = 256;
	int blocksPerGrid = (numElements + threadsPerBlock - 1) / threadsPerBlock;
	printf("CUDA kernel launch with %d blocks of %d threads", blocksPerGrid, threadsPerBlock);
	scaleArray<<<blocksPerGrid, threadsPerBlock>>>(d_A, scalar, numElements);
	err = cudaGetLastError();

	if (err != cudaSuccess) {
		fprintf(stderr, "Failed to launch vectorAdd kernel (error code %s)!\n", cudaGetErrorString(err));
		exit(EXIT_FAILURE);
	}

	// Copy the device result vector in device memory to the host result vector
	// in host memory.
	printf("Copy output data from the CUDA device to the host memory\n");
	err = cudaMemcpy(h_A, d_A, size, cudaMemcpyDeviceToHost);

	if (err != cudaSuccess) {
		fprintf(stderr, "Failed to copy vector C from device to host (error code %s)!\n", cudaGetErrorString(err));
		exit(EXIT_FAILURE);
	}

	for (int i = 0; i < numElements; i++) {
		printf("host[%d] = %d\n", i, h_A[i]);
	}

	// Free device global memory
	err = cudaFree(d_A);

	if (err != cudaSuccess)	{
		fprintf(stderr, "Failed to free device vector A (error code %s)!\n", cudaGetErrorString(err));
		exit(EXIT_FAILURE);
	}

	// Free host memory
	free(h_A);

	// Reset the device and exit
	// cudaDeviceReset causes the driver to clean up all state. While
	// not mandatory in normal operation, it is good practice.  It is also
	// needed to ensure correct operation when the application is being
	// profiled. Calling cudaDeviceReset causes all profile data to be
	// flushed before the application exits
	err = cudaDeviceReset();

	if (err != cudaSuccess)	{
		fprintf(stderr, "Failed to deinitialize the device! error=%s\n", cudaGetErrorString(err));
		exit(EXIT_FAILURE);
	}

	printf("Done\n");
	return 0;
}
