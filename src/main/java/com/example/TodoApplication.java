package com.example;

import com.example.api.TodoResource;
import com.example.core.Todo;
import com.example.core.TodoDAO;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class TodoApplication extends Application<TodoConfiguration> {

    private final HibernateBundle<TodoConfiguration> hibernateBundle = new HibernateBundle<TodoConfiguration>(Todo.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TodoConfiguration configuration){
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Todo";
    }

    @Override
    public void initialize(final Bootstrap<TodoConfiguration> bootstrap) {
        EnvironmentVariableSubstitutor substitutor=new EnvironmentVariableSubstitutor(true);
        SubstitutingSourceProvider provider=new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),substitutor);
        bootstrap.setConfigurationSourceProvider(provider);
        System.out.println(bootstrap.getConfigurationSourceProvider());
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final TodoConfiguration configuration,
                    final Environment environment) {
        String dbUrl = configuration.getDataSourceFactory().getUrl();
        System.out.println("Database url :"+dbUrl);
        final TodoDAO dao = new TodoDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TodoResource(dao));
    }

}
