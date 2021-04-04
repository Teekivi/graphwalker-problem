package com.example.modelimplementations;

import org.graphwalker.core.machine.ExecutionContext;

import com.example.SecondView;

public class SecondViewTest extends ExecutionContext implements SecondView {
    @Override
    public void e_EnterSecondViewSubNode() {
    }

    @Override
    public void v_SecondView() {
        // Using try-catch, otherwise the error might not be logged
        try {
            System.out.println("SecondViewTest: getting global.testAttribute");
            System.out.println(
                    "SecondViewTest: global.testAttribute = " + getAttribute("global.testAttribute").asString()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void v_SecondViewSubNode() {
    }

    @Override
    public void e_LeaveSecondViewSubNode() {
    }
}
