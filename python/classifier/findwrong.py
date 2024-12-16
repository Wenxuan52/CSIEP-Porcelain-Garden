from PIL import Image
import matplotlib.pyplot as plt
import numpy as np
import os
from PIL import ImageFile

ImageFile.LOAD_TRUNCATED_IMAGES = True

a = '明'

path = 'H://数据集//文物鉴别大创项目 瓷器数据集//瓷器数据库//已分类//' + a
fl = os.listdir(path)

path1 = 'H://数据集//文物鉴别大创项目 瓷器数据集//瓷器数据库//dataset//' + a
fl1 = os.listdir(path1)

for i in fl:
    if i[-3:] != 'jpg':
        fl.remove(i)

wrong = []

for i in fl:
    if i in fl1:
        continue
    else:
        wrong.append(i)

print(wrong)