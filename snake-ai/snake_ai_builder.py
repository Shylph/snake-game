# (1)1-1/R
import tensorflow as tf

# [x1,x2]

x_data = []
# left : [1,0,0,0],   right : [0,1,0,0],    up : [0,0,1,0],   down : [0,0,0,1],    none : [0, 0, 0, 0]
y_data = []

for i in range(0, 1600, 10):
    for j in range(0, 900, 10):
        data = [0, 0, 0, 0]  # None
        if i < 600:
            data = [0, 1, 0, 0]  # right
        if j < 500:
            data = [0, 0, 0, 1]  # down
        if j > 600:
            data = [0, 0, 1, 0]  # up
        if i > 1000:
            data = [1, 0, 0, 0]  # left
        x_data.append([i, j])
        y_data.append(data)

X = tf.placeholder(tf.float32, [None, 2], name='x')
Y = tf.placeholder(tf.float32, [None, 4], name='y')

W1 = tf.Variable(tf.random_normal([2, 4]))
b1 = tf.Variable(tf.random_normal([4]))
L1 = tf.nn.relu(tf.matmul(X, W1) + b1)

# ------- 2 inputs 4 neurons
W = tf.Variable(tf.random_normal([4, 4]))
b = tf.Variable(tf.random_normal([4]))

output = tf.matmul(L1, W) + b
h = tf.identity(output, name='h')

# ----- learning
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=output, labels=Y))

train = tf.train.AdamOptimizer(learning_rate=0.001).minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for step in range(10001):
    sess.run(train, feed_dict={X: x_data, Y: y_data})

    if step % 1000 == 0:
        print(step, sess.run(cost, feed_dict={X: x_data, Y: y_data}))

# ----- testing(classification)
x_data = []
y_data = []
for i in range(0, 1600):
    for j in range(0, 900):
        data = [0, 0, 0, 0]
    if i < 600:
        # data = [2]
        data = [0, 1, 0, 0]
    if j < 500:
        # data = [4]
        data = [0, 0, 0, 1]
    if j > 600:
        # data = [3]
        data = [0, 0, 1, 0]
    if i > 1000:
        # data = [1]
        data = [1, 0, 0, 0]
    x_data.append([i, j])
    y_data.append(data)

predicted = tf.equal(tf.argmax(output, 1), tf.argmax(Y, 1))
accuracy = tf.reduce_mean(tf.cast(predicted, tf.float32))

h = sess.run(output, feed_dict={X: x_data})
print("\nLogits: ", h)

p = sess.run(predicted, feed_dict={X: x_data, Y: y_data})
print("Predicted: ", p)

a = sess.run(accuracy, feed_dict={X: x_data, Y: y_data})
print("Accuracy(%): ", a * 100)

# save model
builder = tf.saved_model.builder.SavedModelBuilder("./../model/simple")
builder.add_meta_graph_and_variables(sess, ["test"])
builder.save()
