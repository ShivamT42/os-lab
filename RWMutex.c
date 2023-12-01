#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

int N=3;
pthread_mutex_t db, mutex;
int rc = 0;
void read_db(int i)
{
    printf("\nreader %d is reading", i);
}
void use_data()
{
    printf("\nuse read data");
}
void reader(void *i)
{
    while (1)
    {
        pthread_mutex_lock(&mutex);
        printf("\nreader %d is trying to enter into database", i);
        rc = rc + 1;
        if (rc == 1)
        {
            pthread_mutex_lock(&db);
        }
        printf("\nreader %d entered", i);
        pthread_mutex_unlock(&mutex);
        read_db(((int)i));
        pthread_mutex_lock(&mutex);
        rc = rc - 1;
        printf("\nreader %d is exiting database", i);
        if (rc == 0)
        {
            pthread_mutex_unlock(&db);
        }
        pthread_mutex_unlock(&mutex);
       
        sleep(2);
    }
}
void think_up_data(int i)
{
    printf("\n\twriter %d thinking data", i);
}
void write_database(int i)
{
    printf("\n\twriter %d writing database", i);
   
}
void writer(void *i)
{
    while (1)
    {
        think_up_data((int)i);
        printf("\n\twriter %d trying to enter into database", i);
        pthread_mutex_lock(&db);
        printf("\n\twriter %d is entered into database", i);
        write_database((int)i);
        printf("\n\twriter %d is exiting database", i);
        pthread_mutex_unlock(&db);
        sleep(2);
    }
}
int main()
{
    pthread_t r[N], w[N];
    pthread_mutex_init(&db, NULL);
    pthread_mutex_init(&mutex, NULL);
    void *status;
    for (int i = 0; i < N; i++)
    {
        pthread_create(&w[i], NULL, (void *)&writer, (void *)i);
        pthread_create(&r[i], NULL, (void *)&reader, (void *)i);
    }
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
        pthread_mutex_destroy(&db);
        pthread_mutex_destroy(&mutex);
    }
}