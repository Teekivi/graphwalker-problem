package com.example.runners;

import java.util.concurrent.TimeUnit;

import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.model.Vertex;
import org.graphwalker.java.test.TestExecutor;

import com.example.modelimplementations.FirstViewTest;
import com.example.modelimplementations.SecondViewTest;

public class GraphWalkerProblemRunner {
    public static void main(String[] args) {
        TestExecutor executor = new TestExecutor(
                new FirstViewTest().setPathGenerator(new AStarPath(new ReachedVertex("v_SecondView")))
                        .setCurrentElement(new Vertex().build()),
                new SecondViewTest().setPathGenerator(new RandomPath(new TimeDuration(10, TimeUnit.SECONDS)))
        );
        executor.execute(true);
    }
}
