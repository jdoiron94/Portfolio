#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#define WIDTH 800
#define HEIGHT 608

struct Complex {

	float real;
	float imaginary;

	__device__ Complex(const float r, const float i) {
		real = r;
		imaginary = i;
	}

	__device__ float magnitude2(void) {
		return (real * real) + (imaginary * imaginary);
	}

	__device__ Complex operator *(const Complex& a) {
		return Complex((real * a.real) - (imaginary * a.imaginary), (imaginary * a.real) + (real * a.imaginary));
	}

	__device__ Complex operator +(const Complex& a) {
		return Complex(real + a.real, imaginary + a.imaginary);
	}
};

__device__ int julia(const int x, const int y) {
	const float column = (((float) x / WIDTH) * 3.5F) - 1.75F;
	const float row = (((float) y / HEIGHT) * 3.5F) - 1.75F;
	
	struct Complex c(-0.8F, 0.15F);
	struct Complex a(column, row);

	for (int i = 0; i < 200; i++) {
		a = (a * a) + c;
		if (a.magnitude2() > 1000) {
			return 0;
		}
	}
	return 1;
}

__global__ void kernel(unsigned char *buffer) {
	const int x = (blockIdx.x * blockDim.x) + threadIdx.x;
	const int y = (blockIdx.y * blockDim.y) + threadIdx.y;
	const int offset = (y * gridDim.x * blockDim.x) + x;
	const int juliaValue = julia(x, y);
	const int index = offset * 4;
	buffer[index] = 0;
	buffer[index + 1] = (x * 256) / 800 * juliaValue;
	buffer[index + 2] = (y * 256) / 608 * juliaValue;
}

int main(void) {
	CPUBitmap bitmap(WIDTH, HEIGHT);
	unsigned char *dev_bitmap;
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

	for (int i = 0; i < 1000; i++) {
		kernel<<<grid_size, block_size>>>(dev_bitmap);
	}

	HANDLE_ERROR(cudaMemcpy(bitmap.get_ptr(), dev_bitmap, bitmap.image_size(), cudaMemcpyDeviceToHost));

	HANDLE_ERROR(cudaEventRecord(stop, 0));
	HANDLE_ERROR(cudaEventSynchronize(stop));

	HANDLE_ERROR(cudaEventElapsedTime(&elapsed, start, stop));

	printf("Julia fractal created 1000x in %05.1f ms\n", elapsed);

	bitmap.display_and_exit();

	HANDLE_ERROR(cudaEventDestroy(start));
	HANDLE_ERROR(cudaEventDestroy(stop));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_start));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_stop));
	HANDLE_ERROR(cudaFree(dev_bitmap));
}