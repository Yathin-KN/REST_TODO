package com.example;

import com.example.core.Todo;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class TodoApplication extends Application<TodoConfiguration> {

    private final HibernateBundle<TodoConfiguration> hibernateBundle = new HibernateBundle<TodoConfiguration>(Todo.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TodoConfiguration configuration){
//            return configuration
        }
    }
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
