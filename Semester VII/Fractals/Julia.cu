#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#define WIDTH 800
#define HEIGHT 608

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
	const float column = (((float) x / WIDTH) * 3.5F) - 1.75F;
	const float row = (((float) y / HEIGHT) * 3.5F) - 1.75F;
	
	cuComplex c(-0.8F, 0.15F);
	cuComplex a(column, row);

	for (int i = 0; i < 200; i++) {
		a = (a * a) + c;
		if (a.magnitude2() > 2) {
			return 0;
		}
	}
	return 1;
}

__global__ void kernel(char *buffer) {
	const int x = (blockIdx.x * blockDim.x) + threadIdx.x;
	const int y = (blockIdx.y * blockDim.y) + threadIdx.y;
	const int offset = (y * gridDim.x * blockDim.x) + x;
	const int juliaValue = julia(x, y);
	const int index = offset * 4;
	buffer[index + 1] = (x * 256) / 800 * juliaValue;
	buffer[index + 2] = (y * 256) / 608 * juliaValue;
}

int main(void) {
	CPUBitmap bitmap(WIDTH, HEIGHT);
	char *dev_bitmap;
	float elapsed;

	dim3 block_size(16, 16);
	dim3 grid_size(WIDTH / block_size.x, HEIGHT / block_size.y);

	cudaEvent_t start, stop;
	cudaEvent_t bitmapCpy_start, bitmapCpy_stop;

	HANDLE_ERROR(cudaEventCreate(&start));
	HANDLE_ERROR(cudaEventCreate(&stop));
	HANDLE_ERROR(cudaEventCreate(&bitmapCpy_start));
	HANDLE_ERROR(cudaEventCreate(&bitmapCpy_stop));

	HANDLE_ERROR(cudaMalloc((void**) &dev_bitmap, bitmap.image_size()));

	HANDLE_ERROR(cudaEventRecord(start, 0));

	kernel<<<grid_size, block_size>>>(dev_bitmap);

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