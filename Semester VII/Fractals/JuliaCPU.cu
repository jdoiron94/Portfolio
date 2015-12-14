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

int julia(const int x, const int y) {
  const float column = (((float) x / WIDTH) * 3.5F) - 1.75F;
  const float row = (((float) y / HEIGHT) * 3.5F) - 1.75F;

  Complex c(-0.8F, 0.15F);
  Complex a(column, row);

  for (int i = 0; i < 200; i++) {
    a = (a * a) + c;
    if (a.magnitude2() > 1000) {
      return 0;
    }
  }
  return 1;
}

void kernel(unsigned char *buffer) {
  for (int x = 0; x < WIDTH; x++) {
    for (int y = 0; y < HEIGHT; y++) {
      const int offset = (y * WIDTH) + x;
      const int juliaValue = julia(x, y);
      const int index = offset * 4;
      buffer[index] = 0;
      buffer[index + 1] = (x * 256) / 800 * juliaValue;
      buffer[index + 2] = (y * 256) / 608 * juliaValue;
      buffer[index + 3] = 255;
    }
  }
}

int main(void) {
  CPUBitmap bitmap(WIDTH, HEIGHT);
  time_t start = time(0);
  unsigned char *buffer = bitmap.get_ptr();
  for (int i = 0; i < 1000; i++) {
    kernel(buffer);
  }
  time_t end = time(0);
  printf("Julia fractal created 1000x in %03.0f secs\n", difftime(end, start));
  bitmap.display_and_exit();
}
