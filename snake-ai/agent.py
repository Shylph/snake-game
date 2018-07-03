# 게임 구현과 DQN 모델을 이용해 게임을 실행하고 학습을 진행합니다.

import shutil

import numpy as np
import tensorflow as tf

from game_compatible_layer import Game
from model import DQN

# 최대 학습 횟수
MAX_EPISODE = 1
# action: 0: 좌, 1: 우, 2: 상  3: 하
NUM_ACTION = 4
SCREEN_WIDTH = 80
SCREEN_HEIGHT = 45
# 4 프레임마다 한 번씩 학습합니다.
TRAIN_INTERVAL = 1
# 1000번의 학습마다 한 번씩 타겟 네트웍을 업데이트합니다.
TARGET_UPDATE_INTERVAL = 1
# 학습 데이터를 어느정도 쌓은 후, 일정 시간 이후에 학습을 시작하도록 합니다.
OBSERVE = 70


def train():
    print('뇌세포 깨우는 중..')
    sess = tf.Session()

    game = Game(SCREEN_WIDTH, SCREEN_HEIGHT)
    brain = DQN(sess, SCREEN_WIDTH, SCREEN_HEIGHT, NUM_ACTION)

    rewards = tf.placeholder(tf.float32, [None])
    tf.summary.scalar('avg.reward/ep.', tf.reduce_mean(rewards))

    sess.run(tf.global_variables_initializer())

    # 타겟 네트웍을 초기화합니다.
    brain.update_target_network()

    time_step = 0
    epsilon = 1.0

    for episode in range(MAX_EPISODE):
        # 게임을 시작합니다.
        terminal = False

        # 게임을 초기화하고 현재 상태를 가져옵니다.
        # 상태는 screen_width x screen_height 크기의 화면 구성입니다.
        _, state, _, _ = game.first_step()
        brain.init_state(state)

        while not terminal:
            # 게임 기록을 가져옵니다.
            action, state, reward, terminal = game.step()

            # 현재 상태를 Brain에 기억시킵니다.
            # 기억한 상태를 이용해 학습하고, 다음 상태에서 취할 행동을 결정합니다.
            brain.remember(state, action, reward, terminal)
            if (time_step > OBSERVE) and (time_step % TRAIN_INTERVAL) == 0:
                brain.train()
            # 타겟 네트웍을 업데이트 해 줍니다.
            # if (time_step % TARGET_UPDATE_INTERVAL) == 0:
            #     brain.update_target_network()
            time_step += 1
        # if episode % 50 == 0:
        print(episode)
    save_model(sess)


# java에서 사용할 모델을 저장합니다.
def save_model(sess):
    path = "./../model/simple"
    shutil.rmtree(path)
    builder = tf.saved_model.builder.SavedModelBuilder(path)
    builder.add_meta_graph_and_variables(sess, ["test"])
    builder.save()


train()
