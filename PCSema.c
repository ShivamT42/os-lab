#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>

#define BUFFER_SIZE 5

int buffer[BUFFER_SIZE];
sem_t empty, full, mutex;

int in = 0, out = 0;
int buffer_count = 0;

void *producer(void *arg) {
    int item;

    while (1) {
        item = rand() % 100;
        if (buffer_count == BUFFER_SIZE) {
            printf("Buffer is full\n");
        }

        sem_wait(&empty);

        sem_wait(&mutex);
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        buffer_count++;
        sem_post(&mutex);

        sem_post(&full);

        printf("Produced: %d\tBuffer Count: %d\n", item, buffer_count);

        
    }

    pthread_exit(NULL);
}

void *consumer(void *arg) {
    int item;

    while (1) {
        if (buffer_count == 0) {
            printf("Buffer is empty\n");
        }
        sem_wait(&full);

        sem_wait(&mutex);
        item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        buffer_count--;
        sem_post(&mutex);

        sem_post(&empty);

        printf("Consumed: %d\tBuffer Count: %d\n", item, buffer_count);

        
    }

    pthread_exit(NULL);
}

int main() {
    pthread_t producer_thread, consumer_thread;

    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full, 0, 0);
    sem_init(&mutex, 0, 1);  

    pthread_create(&producer_thread, NULL, producer, NULL);
    pthread_create(&consumer_thread, NULL, consumer, NULL);

    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);

    sem_destroy(&empty);
    sem_destroy(&full);
    sem_destroy(&mutex);

    return 0;
}
