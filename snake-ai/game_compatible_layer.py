import csv
from collections import defaultdict


class Game:
    def __init__(self, screen_width, screen_height):
        self.screen_width = screen_width
        self.screen_height = screen_height
        self.max_step = 0
        self.states = []

        self.step_cnt = 0

        f = open('./../state/state.csv', 'r')
        self.columns = defaultdict(list)  # each value in each column is appended to a list
        csv_reader = csv.DictReader(f)
        for row in csv_reader:
            temp_rows = []
            temp_row = []
            for (i, v) in row.items():
                self.columns[i].append(v)
                if (i != "action") and (i != "reward") and (i != "gameOverFlag"):
                    if int(i) % self.screen_width == 0:
                        temp_rows.append(temp_row)
                        temp_row.clear()
                    temp_row.append(v)
            self.states.append(temp_rows)
            self.max_step += 1
        f.close()

    def step(self):
        if self.step_cnt < self.max_step:
            action = self.columns['action'][self.step_cnt]
            reward = self.columns['reward'][self.step_cnt]
            game_over = self.columns['gameOverFlag'][self.step_cnt]
            state = self.states[self.step_cnt]
            self.step_cnt += 1
        else:
            self.step_cnt -= 1
            action = self.columns['action'][self.step_cnt]
            reward = self.columns['reward'][self.step_cnt]
            game_over = "TRUE"
            state = self.states[self.step_cnt]

        return action, state, reward, game_over == "TRUE"

    def first_step(self):
        self.step_cnt = 0
        return self.step()
