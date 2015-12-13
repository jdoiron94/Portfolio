#include "./common/book.h"
#include "./common/cpu_bitmap.h"

#include <time.h>

#define WIDTH 800
#define HEIGHT 608

/**
 * This method takes an x and y coordinate and subdivides up the values.
 * It checks to see if the coorinate is at the top left edge of the square
 * and will then subdivide the square into nine, removing the centermost.
 * It then does the same to each of the other eight squares
 */
int sierpinski(int x, int y) {
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
 * Runs our kernel
 */
void kernel(unsigned char *buffer) {
  for (int x = 0; x < WIDTH; x++) {
    for (int y = 0; y < HEIGHT; y++) {
      const int offset = (y * WIDTH) + x;
      const int sier = sierpinski(x, y);
      const int index = offset * 4;
      buffer[index] = 0;
      buffer[index + 1] = (x * 256) / 800 * sier;
      buffer[index + 2] = (y * 256) / 608 * sier;
      buffer[index + 3] = 255;
    }
  }
}

/**
 * Main method to time our kernel and display the bitmap
 */
int main(void) {
	CPUBitmap bitmap(WIDTH, HEIGHT);
	time_t start = time(0);
	unsigned char *buffer = bitmap.get_ptr();
	for (int i = 0; i < 1000; i++) {
		kernel(buffer);
	}
	time_t end = time(0);
	printf("Sierpinski carpet fractal created in %03.0f secs\n", difftime(end, start));
	bitmap.display_and_exit();
}
