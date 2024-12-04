package com.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class TodoApplication extends Application<TodoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Todo";
    }

    @Override
    public void initialize(final Bootstrap<TodoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TodoConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
