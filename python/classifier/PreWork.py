'''
PreWork.py
功能：实现对指定大小的生成图片进行sample与label分类制作
-------瓷器鉴别-------
'''

import os
import numpy as np
from PIL import Image
import tensorflow as tf
import matplotlib.pyplot as plt
from numpy import *

# 近现代
modern = []
label_modern = []
# 清朝
Qing = []
label_Qing = []
# 明朝
Ming = []
label_Ming = []
# 元朝
Yuan = []
label_Yuan = []
# 宋~元
betweenSongYuan = []
label_betweenSongYuan = []
# 宋朝
Song = []
label_Song = []
# 五代十国~宋
betweenFDTKSong = []
label_betweenFDTKSong = []
# 五代十国
FDTK = []
label_FDTK = []
# 十六国~唐
betweenSixKTang = []
label_betweenSixKTang = []
# 晋~汉
betweenJinHan = []
label_betweenJinHan = []
# 秦~石器时代
Qin = []
label_Qin = []


def get_file(file_dir):
    # step1：获取路径下所有的图片路径名，存放到
    # 对应的列表中，同时贴上标签，存放到label列表中。
    for file in os.listdir(file_dir + '/近现代'):
        modern.append(file_dir + '/近现代' + '/' + file)
        label_modern.append(0)
    for file in os.listdir(file_dir + '/清'):
        Qing.append(file_dir + '/清' + '/' + file)
        label_Qing.append(1)
    for file in os.listdir(file_dir + '/明'):
        Ming.append(file_dir + '/明' + '/' + file)
        label_Ming.append(2)
    for file in os.listdir(file_dir + '/元'):
        Yuan.append(file_dir + '/元' + '/' + file)
        label_Yuan.append(3)
    for file in os.listdir(file_dir + '/宋~元'):
        betweenSongYuan.append(file_dir + '/宋~元' + '/' + file)
        label_betweenSongYuan.append(4)
    for file in os.listdir(file_dir + '/宋'):
        Song.append(file_dir + '/宋' + '/' + file)
        label_Song.append(5)
    for file in os.listdir(file_dir + '/五代十国~宋'):
        betweenFDTKSong.append(file_dir + '/五代十国~宋' + '/' + file)
        label_betweenFDTKSong.append(6)
    for file in os.listdir(file_dir + '/五代十国'):
        FDTK.append(file_dir + '/五代十国' + '/' + file)
        label_FDTK.append(7)
    for file in os.listdir(file_dir + '/十六国~唐'):
        betweenSixKTang.append(file_dir + '/十六国~唐' + '/' + file)
        label_betweenSixKTang.append(8)
    for file in os.listdir(file_dir + '/晋~汉'):
        betweenJinHan.append(file_dir + '/晋~汉' + '/' + file)
        label_betweenJinHan.append(9)
    for file in os.listdir(file_dir + '/秦~石器时代'):
        Qin.append(file_dir + '/秦~石器时代' + '/' + file)
        label_Qin.append(10)

    # 打印出提取图片的情况，检测是否正确提取
    print("近现代有 %d 张\n清朝有 %d 张\n明朝有 %d 张\n" % (
        len(modern), len(Qing), len(Ming)), end="")
    print("元朝有 %d 张\n宋~元有 %d 张\n宋朝有 %d 张\n" % (
        len(Yuan), len(betweenSongYuan), len(Song)))
    print("五代十国~宋有 %d 张\n五代十国有 %d 张\n十六国~唐有 %d 张\n" % (
        len(betweenFDTKSong), len(FDTK), len(betweenSixKTang)))
    print("晋~汉有 %d 张\n秦~石器时代有 %d 张\n" % (
        len(betweenJinHan), len(Qin)))

    # step2：对生成的图片路径和标签List做打乱处理把所有的合起来组成一个list（img和lab）
    # 合并数据numpy.hstack(tup)
    # tup可以是python中的元组（tuple）、列表（list），或者numpy中数组（array），函数作用是将tup在水平方向上（按列顺序）合并
    image_list = np.hstack((modern, Qing, Ming, Yuan, betweenSongYuan, Song, betweenFDTKSong, FDTK, betweenSixKTang,
                            betweenJinHan, Qin))
    label_list = np.hstack((label_modern, label_Qing, label_Ming, label_Yuan, label_betweenSongYuan, label_Song,
                            label_betweenFDTKSong, label_FDTK, label_betweenSixKTang, label_betweenJinHan, label_Qin))
    # 利用shuffle，转置、随机打乱
    temp = np.array([image_list, label_list])  # 转换成2维矩阵
    temp = temp.transpose()  # 转置
    # numpy.transpose(a, axes=None) 作用：将输入的array转置，并返回转置后的array
    np.random.shuffle(temp)  # 按行随机打乱顺序函数

    # 将所有的img和lab转换成list
    all_image_list = list(temp[:, 0])  # 取出第0列数据，即图片路径
    all_label_list = list(temp[:, 1])  # 取出第1列数据，即图片标签
    label_list = [int(i) for i in label_list]  # 转换成int数据类型

    '''
    # 将所得List分为两部分，一部分用来训练tra，一部分用来验证val
    n_sample = len(all_label_list)
    n_val = int(math.ceil(n_sample * ratio))  # 验证样本数, ratio是验证集的比例
    n_train = n_sample - n_val  # 训练样本数
    tra_images = all_image_list[0:n_train]
    tra_labels = all_label_list[0:n_train]
    tra_labels = [int(float(i)) for i in tra_labels]  # 转换成int数据类型
    val_images = all_image_list[n_train:-1]
    val_labels = all_label_list[n_train:-1]
    val_labels = [int(float(i)) for i in val_labels]  # 转换成int数据类型
    return tra_images, tra_labels, val_images, val_labels
    
    '''
    return image_list, label_list


# 将image和label转为list格式数据，因为后边用到的的一些tensorflow函数接收的是list格式数据
# 为了方便网络的训练，输入数据进行batch处理
# image_W, image_H, ：图像高度和宽度
# batch_size：每个batch要放多少张图片
# capacity：一个队列最大多少
def get_batch(image, label, image_W, image_H, batch_size, capacity):
    # step1：将上面生成的List传入get_batch() ，转换类型，产生一个输入队列queue
    # tf.cast()用来做类型转换
    image = tf.cast(image, tf.string)  # 可变长度的字节数组.每一个张量元素都是一个字节数组
    label = tf.cast(label, tf.int32)
    # tf.train.slice_input_producer是一个tensor生成器
    # 作用是按照设定，每次从一个tensor列表中按顺序或者随机抽取出一个tensor放入文件名队列。
    input_queue = tf.train.slice_input_producer([image, label])
    label = input_queue[1]
    image_contents = tf.read_file(input_queue[0])  # tf.read_file()从队列中读取图像

    # step2：将图像解码，使用相同类型的图像
    image = tf.image.decode_jpeg(image_contents, channels=3)
    # jpeg或者jpg格式都用decode_jpeg函数，其他格式可以去查看官方文档

    # step3：数据预处理，对图像进行旋转、缩放、裁剪、归一化等操作，让计算出的模型更健壮。
    image = tf.image.resize_image_with_crop_or_pad(image, image_W, image_H)
    # 对resize后的图片进行标准化处理
    image = tf.image.per_image_standardization(image)

    # step4：生成batch
    # image_batch: 4D tensor [batch_size, width, height, 3], dtype = tf.float32
    # label_batch: 1D tensor [batch_size], dtype = tf.int32
    image_batch, label_batch = tf.train.batch([image, label], batch_size=batch_size, num_threads=16, capacity=capacity)

    # 重新排列label，行数为[batch_size]
    label_batch = tf.reshape(label_batch, [batch_size])
    # image_batch = tf.cast(image_batch, tf.uint8)    # 显示彩色图像
    image_batch = tf.cast(image_batch, tf.float32)  # 显示灰度图
    # print(label_batch) Tensor("Reshape:0", shape=(6,), dtype=int32)
    return image_batch, label_batch
    # 获取两个batch，两个batch即为传入神经网络的数据

'''
def PreWork():
    # 对预处理的数据进行可视化，查看预处理的效果
    IMG_W = 224
    IMG_H = 224
    BATCH_SIZE = 6
    CAPACITY = 64
    ratio = 0.2
    train_dir = 'H:/数据集/文物鉴别大创项目 瓷器数据集/瓷器数据库/dataset'
    # image_list, label_list, val_images, val_labels = get_file(train_dir)
    image_list, label_list = get_file(train_dir)
    image_batch, label_batch = get_batch(image_list, label_list, IMG_W, IMG_H, BATCH_SIZE, CAPACITY)
    print(label_batch.shape)
    lists = ('modern', 'Qing', 'Ming', 'Yuan', 'betweenSongYuan', 'Song', 'betweenFDTKSong', 'FDTK', 'betweenSixKTang',
             'betweenJinHan', 'Qin')
    with tf.Session() as sess:
        i = 0
        coord = tf.train.Coordinator()  # 创建一个线程协调器，用来管理之后在Session中启动的所有线程
        threads = tf.train.start_queue_runners(coord=coord)
        try:
            while not coord.should_stop() and i < 1:
                # 提取出两个batch的图片并可视化。
                img, label = sess.run([image_batch, label_batch])  # 在会话中取出img和label
                # img = tf.cast(img, tf.uint8)
                '''
'''
                # 1、range()返回的是range object，而np.arange()返回的是numpy.ndarray()
                # range(start, end, step)，返回一个list对象，起始值为start，终止值为end，但不含终止值，步长为step。只能创建int型list。
                # arange(start, end, step)，与range()类似，但是返回一个array对象。需要引入import numpy as np，并且arange可以使用float型数据。
                # 
                # 2、range()不支持步长为小数，np.arange()支持步长为小数
                # 
                # 3、两者都可用于迭代
                # range尽可用于迭代，而np.nrange作用远不止于此，它是一个序列，可被当做向量使用。
                '''
'''
                for j in np.arange(BATCH_SIZE):
                    # np.arange()函数返回一个有终点和起点的固定步长的排列
                    print('label: %d' % label[j])
                    plt.imshow(img[j, :, :, :])
                    title = lists[int(label[j])]
                    plt.title(title)
                    plt.show()
                i += 1
        except tf.errors.OutOfRangeError:
            print('done!')
        finally:
            coord.request_stop()
        coord.join(threads)


if __name__ == '__main__':
    PreWork()

'''