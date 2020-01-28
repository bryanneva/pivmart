package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:product-api:+:8081",
        // NOTE: You'll need to replace the section below to point to your
        // pivmart root folder
        repositoryRoot = "stubs://file://Users/abray/workspace/pivmart/nexus/META-INF",
        properties="stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class CatalogClientContractTests {

    @Autowired
    private CatalogClient catalogClient;

    @Test
    public void findAll() {
        fail("Add test to verify consumption of catalog contract");
    }
}
