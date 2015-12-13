#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#define WIDTH 800
#define HEIGHT 608

/**
 * This method takes an x and y coordinate and subdivides up the values.
 * It checks to see if the coorinate is at the top left edge of the square
 * and will then subdivide the square into nine, removing the centermost.
 * It then does the same to each of the other eight squares
 */
__device__ int sierpinski(int x, int y) {
	for (;x > 0 || y > 0;) {
		if (x % 3 == 1 && y % 3 == 1) {
			return 0;
		}
		x /= 3;
		y /= 3;
	}
	return 1;
}

/**
 * Runs our GPU kernel
 */
__global__ void kernel(unsigned char *buffer) {
	const int x = (blockIdx.x * blockDim.x) + threadIdx.x;
	const int y = (blockIdx.y * blockDim.y) + threadIdx.y;
	const int offset = (y * gridDim.x * blockDim.x) + x;
	const int sier = sierpinski(x, y);
	const int index = offset * 4;
	buffer[index] = 0;
	buffer[index + 1] = (x * 256) / 800 * sier;
	buffer[index + 2] = (y * 256) / 608 * sier;
	buffer[index + 3] = 255;
}

/**
 * Main method to time our GPU kernel 1000x and display the bitmap
 */
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

	printf("Sierpinski carpet fractal created 1000x and copied back to host memory in %3.1f ms\n", elapsed);

	bitmap.display_and_exit();

	HANDLE_ERROR(cudaEventDestroy(start));
	HANDLE_ERROR(cudaEventDestroy(stop));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_start));
	HANDLE_ERROR(cudaEventDestroy(bitmapCpy_stop));
	HANDLE_ERROR(cudaFree(dev_bitmap));
}