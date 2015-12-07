#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#include <time.h>

#define WIDTH 800
#define HEIGHT 608

/**
 * @author Jacob Doiron
 * @since 12/6/15
 */

struct Complex {

  float real;
  float imaginary;

  Complex(const float r, const float i) {
    real = r;
    imaginary = i;
  }

  float magnitude2(void) {
    return (real * real) + (imaginary * imaginary);
  }

  Complex operator *(const Complex& a) {
    return Complex((real * a.real) - (imaginary * a.imaginary), (imaginary * a.real) + (real * a.imaginary));
  }

  Complex operator +(const Complex& a) {
    return Complex(real + a.real, imaginary + a.imaginary);
  }
};

void kernel(unsigned char *buffer) {
  for (int column = 0; column < WIDTH; column++) {
    for (int row = 0; row < HEIGHT; row++) {
      const int offset = (row * WIDTH) + column;
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
  }
}

int main(void) {
  CPUBitmap bitmap(WIDTH, HEIGHT);
  time_t start = time(0);
  unsigned char *buffer = bitmap.get_ptr();
  kernel(buffer);
  time_t end = time(0);
  printf("Mandelbrot fractal created in %03.0f secs\n", difftime(end, start));
  bitmap.display_and_exit();
}
