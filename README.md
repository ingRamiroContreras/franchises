# Franchises API

API desarrollada en **Spring Boot** para gestionar franquicias, sucursales y productos. Incluye ejemplos de integración con Docker y Docker Compose para desplegar fácilmente en entorno local y una sección para la arquitectura en la nube con un diagrama (que puedes agregar posteriormente).

---

## Tabla de Contenidos

- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Ejecutar en local con Docker Compose](#ejecutar-en-local-con-docker)
- [Arquitectura cloud](#arquitectura-cloud)
- [Notas adicionales](#notas-adicionales)

---

## Estructura del proyecto y descripción técnica

El proyecto fue desarrollado utilizando el framework **Spring Boot**, adoptando una arquitectura **Hexagonal** (también conocida como Ports and Adapters), que facilita la separación de responsabilidades, la escalabilidad y el mantenimiento del sistema.

### Entorno de desarrollo local
Para facilitar la experimentación y pruebas en un entorno de desarrollo, se configuró la aplicación para que se despliegue con una base de datos en memoria (H2). Esto elimina la necesidad de infraestructura adicional y permite un ciclo de desarrollo ágil y rápido.

### Implementación mediante Docker
Toda la infraestructura del proyecto, incluyendo la aplicación y la base de datos, se despliega mediante **Docker Compose**. Esto permite levantar toda la arquitectura en contenedores aislados con un solo comando (`docker-compose up`), facilitando la replicación del entorno en diferentes máquinas y mejorando la portabilidad y la consistencia en los despliegues de desarrollo y testing.

### Infraestructura en la nube (entorno productivo)
Para el despliegue en producción, toda la infraestructura fue elevada a la plataforma de **Amazon Web Services (AWS)**. La arquitectura en la nube incluye diversos componentes que aseguran escalabilidad, disponibilidad y seguridad, tales como:

- **Internet Gateway:** facilita la comunicación entre la VPC y el exterior.
- **Load Balancer (ALB):** distribuye el tráfico entrante hacia las instancias en diferentes zonas de disponibilidad.
- **Auto Scaling Groups (ASG):** gestionan automáticamente la cantidad de instancias en función de la demanda, garantizando alta disponibilidad y escalabilidad.
- **RDS (Relational Database Service):** proporciona una base de datos relacional escalable y gestionada, con respaldo y opciones de alta disponibilidad.
- **Cache Service (Redis/Memcached):** mejora el rendimiento mediante almacenamiento en caché de datos de acceso frecuente.

Todo esto fue desplegado siguiendo los principios de **Infraestructura como Código (IaC)**, utilizando herramientas como **CloudFormation** o **Terraform**, que permiten gestionar los recursos de manera versionada, reproducible y automatizada.

---

## Requisitos

- Docker (versión reciente)
- Docker Compose
- Java 17 
- Maven

---

## Ejecutar en local con Docker

1. Es posible cargar el proyecto en un IDE y darle RUN.

2. ejecutar maven package

```bash
./mvnw clean package

```

3. Docker contruir la imagen:

```bash
docker build -t franchises-app .

```

4. Docker Compose subir la app:

- setear la variable de ambiente -> SPRING_PROFILE=dev
- ubicarse en el proyecto donde obtenemos el docker-compose.yml

```bash
docker-compose up -d

```

5. las documentacion de las apis despues de subir el contenedor estan expuestas en http://localhost:8080/swagger-ui/index.html#/ 

---

## Arquitectura cloud

A continuación se presenta un esquema de la arquitectura implementada en AWS para el entorno productivo:

![Diagrama de Arquitectura](ruta/a/tu/diagrama.png)

*Descripción del diagrama:*  
- El **Internet Gateway** permite el acceso externo a la infraestructura, conectando la red interna con internet.  
- El **Application Load Balancer (ALB)** recibe las peticiones provenientes del Internet Gateway y las distribuye entre las instancias del Auto Scaling Group.  
- Las instancias en el **Auto Scaling Group (ASG)** ejecutan la aplicación Spring Boot, conectándose a una base de datos relacional gestionada en **RDS** y a un sistema de caché (como Redis o Memcached).  
- Todo el entorno está construido siguiendo principios de **Infraestructura como Código (IaC)**, lo que facilita la gestión, reproducibilidad y escalabilidad de la infraestructura, incluyendo componentes de seguridad, balanceo de carga y alta disponibilidad.



## Notas adicionales

### estructura de la DB

```sql

conectarse a la  BD desde EC2 sudo docker run -it --rm mysql:8.0 mysql -h nequi-mysqldatabase-6euejcsde5zf.c7g04ay8o057.us-east-2.rds.amazonaws.com -P 3306 -u admin -p

SHOW DATABASES;

USE nequi;

SHOW TABLES;


CREATE TABLE franchises (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE branches (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    franchise_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_franchise FOREIGN KEY (franchise_id) REFERENCES franchises(id)
);

CREATE TABLE products (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    stock INT,
    branch_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_branch FOREIGN KEY (branch_id) REFERENCES branches(id)
);
```