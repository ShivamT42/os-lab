#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>


#define NUM_PHILOSOPHERS 5

pthread_t philosophers[NUM_PHILOSOPHERS]; 
sem_t forks[NUM_PHILOSOPHERS];            

void *philosopher(void *arg)
{
    int id = *((int *)arg); 
    int left_fork = id;
    int right_fork = (id + 1) % NUM_PHILOSOPHERS; 

    while (1)
    {
        printf("Philosopher %d is thinking\n", id);
        sleep(rand() % 5); 

        printf("Philosopher %d is hungry\n", id);

        sem_wait(&forks[left_fork]);
        printf("Philosopher %d picked up left fork %d\n", id, left_fork);

        sem_wait(&forks[right_fork]);
        printf("Philosopher %d picked up right fork %d\n", id, right_fork);

        printf("Philosopher %d is eating\n", id);
        sleep(rand() % 5); 

        sem_post(&forks[right_fork]);
        printf("Philosopher %d put down right fork %d\n", id, right_fork);

        sem_post(&forks[left_fork]);
        printf("Philosopher %d put down left fork %d\n", id, left_fork);
    }
}

int main()
{
    int ids[NUM_PHILOSOPHERS];

    for (int i = 0; i < NUM_PHILOSOPHERS; i++)
    {
        sem_init(&forks[i], 0, 1); 
    }

    for (int i = 0; i < NUM_PHILOSOPHERS; i++)
    {
        ids[i] = i;
        pthread_create(&philosophers[i], NULL, philosopher, &ids[i]); 
    }

    for (int i = 0; i < NUM_PHILOSOPHERS; i++)
    {
        pthread_join(philosophers[i], NULL); 
    }

    for (int i = 0; i < NUM_PHILOSOPHERS; i++)
    {
        sem_destroy(&forks[i]); 
    }

    return 0;
}