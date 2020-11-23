#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdbool.h>

#include <stdlib.h>
// Needed for random numbers

int main()
{
    const char* Pipe="./Rohr";
    unsigned int MC=0;
    unsigned char Message[7];
    
    const bool test_checksum=false;
    const bool test_skip_message=false;

    int fd=open(Pipe, O_WRONLY);
    while (1){
        Message[0]=1;
        Message[1] = (MC >> 24) & 0xFF;
        Message[2] = (MC >> 16) & 0xFF;
        Message[3] = (MC >> 8) & 0xFF;
        Message[4] = MC & 0xFF;
        Message[5] = (Message[0]+Message[1]+Message[2]+Message[3]+Message[4])%256;
        
        // wrong checksum test
        if (test_checksum & (MC%10 ==0))
        {
            Message[5]=random();
        }

        //Message[6] = "\n";
        printf("Sending Message %i\n",MC);
        printf("%i %i %i %i %i \n",Message[0],Message[1],Message[2],Message[3],Message[4]);

        // skip message test
        if (test_skip_message & (MC%10 ==0))
        {}
        else
        {
            write(fd,Message,6);
        }
        
        MC+=1;
        usleep(1000000);
    }
    close(fd);


return 0;
}