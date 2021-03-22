package com.example.runners;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.Context;
import org.graphwalker.core.model.Vertex;
import org.graphwalker.java.test.TestExecutor;

import com.example.modelimplementations.FirstViewTest;
import com.example.modelimplementations.SecondViewTest;

public class GraphWalkerProblemRunner {
    public static void main(String[] args) throws IOException {
        TestExecutor executor = new TestExecutor(
                FirstViewTest.class,
                SecondViewTest.class
        );

        for (Context context : executor.getMachine().getContexts()) {
            if (context instanceof FirstViewTest) {
                context.setPathGenerator(new AStarPath(new ReachedVertex("v_SecondView")));
            } else if( context instanceof SecondViewTest) {
                context.setPathGenerator(new RandomPath(new TimeDuration(10, TimeUnit.SECONDS)));
            }
        }

        executor.execute(true);
    }
}
