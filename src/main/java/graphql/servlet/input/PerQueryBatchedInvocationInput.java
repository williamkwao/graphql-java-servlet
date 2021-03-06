package graphql.servlet.input;

import graphql.schema.GraphQLSchema;
import graphql.servlet.context.GraphQLContext;
import graphql.servlet.core.internal.GraphQLRequest;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A Collection of GraphQLSingleInvocationInput that each have a unique context object.
 */
public class PerQueryBatchedInvocationInput implements GraphQLBatchedInvocationInput {
    private final List<GraphQLSingleInvocationInput> inputs;

    public PerQueryBatchedInvocationInput(List<GraphQLRequest> requests, GraphQLSchema schema, Supplier<GraphQLContext> contextSupplier, Object root) {
        inputs = requests.stream()
            .map(request -> new GraphQLSingleInvocationInput(request, schema, contextSupplier.get(), root)).collect(Collectors.toList());
    }

    @Override
    public List<GraphQLSingleInvocationInput> getExecutionInputs() {
        return inputs;
    }
}
