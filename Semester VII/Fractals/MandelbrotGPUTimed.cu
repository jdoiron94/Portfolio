#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#define WIDTH 800
#define HEIGHT 608

/**
 * @author Jacob Doiron
 * @since 12/6/15
 */

__global__ void kernel(unsigned char *buffer) {
  const int row = (blockIdx.y * blockDim.y) + threadIdx.y;
  const int column = (blockIdx.x * blockDim.x) + threadIdx.x;
  const int offset = (row * gridDim.x * blockDim.x) + column;
  if(column >= WIDTH || row >= HEIGHT) {
    return;
  }
  const float x0 = (((float) column / WIDTH) * 3.5F) - 2.5F;
  const float y0 = (((float) row / HEIGHT) * 3.5F) - 1.75F;
  float x = 0.0F;
  float y = 0.0F;
  float temporary_x;
  int i;
  for (i = 0; i < 100 && (x * x) + (y * y) <= 4.0F; i++) {
    temporary_x = (x * x) - (y * y) + x0;
    y = (2.0F * x * y) + y0;
    x = temporary_x;
  }
  int color = i * 5;
  if (color >= 256) {
    color = 0;
  }
  const int index = offset * 4;
  buffer[index] = 0;
  buffer[index + 1] = color;
  buffer[index + 2] = 0;
  buffer[index + 3] = 255;
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

  printf("Mandelbrot fractal created 1000x in %3.1f ms\n", elapsed);

  bitmap.display_and_exit();

  HANDLE_ERROR(cudaEventDestroy(start));
  HANDLE_ERROR(cudaEventDestroy(stop));
  HANDLE_ERROR(cudaEventDestroy(bitmapCpy_start));
  HANDLE_ERROR(cudaEventDestroy(bitmapCpy_stop));
  HANDLE_ERROR(cudaFree(dev_bitmap));
}
