#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>
#include <time.h>

#define BUFFER_SIZE 5
#define THREAD_NUM 8
int buffer[BUFFER_SIZE];
sem_t empty, full;

pthread_mutex_t mutexBuffer;

//int in = 0, out = 0;
int count = 0;

void *producer(void *arg) {
    int item;

    while (1) {
        item = rand() % 100;
        if (count == BUFFER_SIZE) {
            printf("Buffer is full\n");
        }

        sem_wait(&empty);
        pthread_mutex_lock(&mutexBuffer);
        buffer[count] = item;
        //in = (in + 1) % BUFFER_SIZE;
        count++;
        pthread_mutex_unlock(&mutexBuffer);

        sem_post(&full);

        printf("Produced: %d\tBuffer Count: %d\n", item, count);

        
    }

    pthread_exit(NULL);
}

void *consumer(void *arg) {
    int item;

    while (1) {
        if (count == 0) {
            printf("Buffer is empty\n");
        }
        sem_wait(&full);

        pthread_mutex_lock(&mutexBuffer);
        item = buffer[count];
        //out = (out + 1) % BUFFER_SIZE;
        count--;
        pthread_mutex_unlock(&mutexBuffer);

        sem_post(&empty);

        printf("Consumed: %d\tBuffer Count: %d\n", item, count);

        
    }

    pthread_exit(NULL);
}

int main() {
    pthread_t producer_thread, consumer_thread;

    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full, 0, 0);
    pthread_mutex_init(&mutexBuffer, 0); 

    pthread_create(&producer_thread, NULL, producer, NULL);
    pthread_create(&consumer_thread, NULL, consumer, NULL);

    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);

    sem_destroy(&empty);
    sem_destroy(&full);
    pthread_mutex_destroy(&mutexBuffer);

    return 0;
}
