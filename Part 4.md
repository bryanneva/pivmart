# Workshop Part 4

## Learning Objectives

## Overview



## Concepts



## Exercise 1 - Make Catalog Browser a separate deployable

1. Move catalog-browser from inside the Pivmart Spring Boot app into the root folder
1. Clean up the build step inside `package.json` so that it no longer copies the built front-end into the Pivmart Spring app
1. Remove any remnants of the front-end application from inside `src/main/resources/static`

## Exercise 2 - Manually Deploy to PCF/TAS

1. Check out the `8.0_deploy_to_tas-starter` branch
1. Read through the diff and note the changes / files we've provided
    * swap dependency to `io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry`
    * add manifest.yml files for each deployable
1. Update each manifest to add a unique suffix to the application `name`, using for example your name
    * example `pivmart-main-SUFFIX` should become `pivmart-main-alicia-jones`
    * (this is to prevent conflicts in hostnames on PCFOne)
1. Run the following commands to deploy manually to PCF One:
    ```
************************************

# Exercise 3 - Setup CI/CD Pipeline








Questions:
* how would this scale?
* our backing data sources come into play here