import os
import cv2
import numpy as np

# ffmpeg -i [input] -filter:v fps=fps=20 %0d.bmp
DATA_PATH = ""
IDEAL_HEIGHT = 20
IDEAL_WIDTH = round(20 / 1080 * 1440)


def files():
    for path in os.listdir(DATA_PATH):
        file = os.path.join(DATA_PATH, path)
        if os.path.isfile(file):
            yield file


def file_to_binary(path: str):
    img = cv2.imread(path)
    img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    img = cv2.resize(img, (IDEAL_WIDTH, IDEAL_HEIGHT))
    img = np.array(img, dtype=np.uint8).flatten()
    return bytearray(img)


def main():
    with open("res/bad_apple.dat", "wb") as output:
        for file in files():
            print(file)
            output.write(file_to_binary(file))


if __name__ == "__main__":
    main()
