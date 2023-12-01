#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>
int N = 3; 
sem_t db, mutex;  
int rc = 0; 
void read_db(int i)  
{
    printf("\nreader %d is reading", i);
    // printf("\nreader %d: %d %d %d", i, h, m, s);
}
void use_data() 
{
    printf("\nuse read data");
}
void reader(void *i)
{
    while (1) 
    {

        sem_wait(&mutex); 
        printf("\nreader %d is trying to enter into database", i);
        rc = rc + 1;
        if (rc == 1)
        {
            sem_wait(&db); 
        }
        printf("\nreader %d entered", i);
        sem_post(&mutex); 
        read_db(((int)i));
        sem_wait(&mutex);
        rc = rc - 1;
        printf("\nreader %d is exiting database", i);
        if (rc == 0)
        {
            sem_post(&db);  
        }
        sem_post(&mutex); 
        
        sleep(2); 
    }
}
void think_up_data(int i)
{
    printf("\nwriter %d thinking data", i);
}
void write_database(int i)
{
    printf("\nwriter %d writing database", i);

}
void writer(void *i)
{
    while (1)
    {
        think_up_data((int)i);
        printf("\nwriter %d trying to enter into database", i);
        sem_wait(&db);
        printf("\nwriter %d is entered into database", i);
        write_database((int)i);
        printf("\nwriter %d is exiting database", i);
        sem_post(&db);
        sleep(2);
    }
}
int main()
{
    pthread_t r[N], w[N];
    sem_init(&db, 0, 1);
    sem_init(&mutex, 0, 1);
    void *status;
    for (int i = 0; i < N; i++)
    {
        pthread_create(&w[i], NULL, (void *)&writer, (void *)i);
        pthread_create(&r[i], NULL, (void *)&reader, (void *)i);
    }
    for (int i = 0; i < N; i++)
    {
        pthread_join(r[i], status);
        pthread_join(w[i], status);
    }
    for (int i = 0; i < N; i++)
    {
        sem_destroy(&db);
        sem_destroy(&mutex);
    }
}