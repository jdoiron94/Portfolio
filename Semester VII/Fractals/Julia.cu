#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#include <stdio.h>
#include <time.h>

#define DIM 1024

struct cuComplex {

	float r;
	float i;

	__device__ cuComplex(const float a, const float b) {
		r = a;
		i = b;
	}

	__device__ float magnitude2(void) {
		return (r * r) + (i * i);
	}

	__device__ cuComplex operator *(const cuComplex& a) {
		return cuComplex((r * a.r) - (i * a.i), (i * a.r) + (r * a.i));
	}

	__device__ cuComplex operator +(const cuComplex& a) {
		return cuComplex(r + a.r, i + a.i);
	}
};

__device__ int julia(const int x, const int y) {
	const float scale = 1.5F;
	const float div_dim = (float) DIM / 2;
	const float julia_x = scale * (div_dim - x) / div_dim;
	const float julia_y = scale * (div_dim - y) / div_dim;
	
	//cuComplex c(-0.8F, 0.156F);
	cuComplex c(-0.8F, 0.15F);
	cuComplex a(julia_x, julia_y);

	for (int i = 0; i < 200; i++) {
		a = (a * a) + c;
		if (a.magnitude2() > 2) {
			return 0;
		}
	}
	return 1;
}

__global__ void kernel(unsigned char *ptr, const int red, const int green, const int blue) {
	const int x = threadIdx.x + (blockIdx.x * blockDim.x);
	const int y = threadIdx.y + (blockIdx.y * blockDim.y);
	const int offset = x + ((y * gridDim.x) * blockDim.x);
	const int juliaValue = julia(x, y);
	int index = offset * 4;
	ptr[index++] = red * juliaValue;
	ptr[index++] = green * juliaValue;
	ptr[index++] = blue * juliaValue;
	ptr[index] = 0;
}

int main(void)
{

	CPUBitmap bitmap(DIM, DIM);

	unsigned char * dev_bitmap;
	float elapsed;

	dim3 grid(DIM, DIM);
	dim3 blocks(DIM / 16, DIM / 16);
	dim3 threads(16, 16);
	dim3 thread(DIM, DIM);

	cudaEvent_t start, stop;
	cudaEvent_t bitmapCpy_start, bitmapCpy_stop;

	srand(time(NULL));

	const int red = rand() % 255;
	const int green = rand() % 255;
	const int blue = rand() % 255;

	HANDLE_ERROR(cudaEventCreate(&start));
	HANDLE_ERROR(cudaEventCreate(&stop));
	HANDLE_ERROR(cudaEventCreate(&bitmapCpy_start));
	HANDLE_ERROR(cudaEventCreate(&bitmapCpy_stop));

	HANDLE_ERROR(cudaMalloc((void**) &dev_bitmap, bitmap.image_size()));

	HANDLE_ERROR(cudaEventRecord(start, 0));

	kernel << <blocks, threads >> >(dev_bitmap, red, green, blue);

	HANDLE_ERROR(cudaMemcpy(bitmap.get_ptr(), dev_bitmap, bitmap.image_size(), cudaMemcpyDeviceToHost));

	HANDLE_ERROR(cudaEventRecord(stop, 0));
	HANDLE_ERROR(cudaEventSynchronize(stop));

	HANDLE_ERROR(cudaEventElapsedTime(&elapsed, start, stop));

	printf("Julia fractal created and copied back to host memory in %3.1f ms\n", elapsed);

	bitmap.display_and_exit();

	HANDLE_ERROR(cudaEventDestroy(start));
	HANDLE_ERROR(cudaEventDestroy(stop));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_start));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_stop));
	HANDLE_ERROR(cudaFree(dev_bitmap));
}