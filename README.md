<img src="https://i.postimg.cc/c15jLGQK/ww1.png" width="60"><img src="https://i.postimg.cc/KzdM05KD/ww2.png" width="60"> <img src="https://i.postimg.cc/FRPPz8Bs/ww4.png" width="60"> <img src="https://i.postimg.cc/xj3tYSvh/kafka.png" width="65" height="60"> <img src="https://i.postimg.cc/NjYbPP4p/download.png" width="85" height="100">










Composer (in the _misc_ folder)

`docker-compose -f docker-composer.yml up -d`

_Topics_ creation

`docker exec -it  [container_id] kafka-topics.sh --create --bootstrap-server localhost:9092 --topic  input-harga`

`docker exec -it  [container_id] kafka-topics.sh --create --bootstrap-server localhost:9092 --topic  topup`

Kafka adjustment:

Accessing _outside_ the docker host

      KAFKA_ADVERTISED_HOST_NAME: localhost
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092

Accessing _inside_ the cluster (where _kafka1_ is that `hostname: kafka1`)

      KAFKA_ADVERTISED_HOST_NAME: kafka1
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka1:9092
      
Exposed ports:

- Kafka

     ports:
    
        - "9092:9092"
        - "29092:29092"
        - "9999:9999"
- Postgres

     ports:
     
        - "5433:5432"


If it is for _some unknown reasons_ things fail in your system, an alternative way is to creating a custome internal network (_bridge_ mode) then attach all containers to that network.

`docker network create -d bridge [network_name]`


`docker run -itd --network=mynet [container_id]`
