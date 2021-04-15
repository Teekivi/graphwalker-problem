# graphwalker-problem
The problem demonstrated in the `replay-machine` Git branch:<br/>
Trying to run a ReplayMachine with two contexts/models succeeds to traverse the first model, but upon entering the
second model, an error occurs. Relevant output from GraphWalkerProblemRunner:

```
Context path generators from ReplayMachine:
com.example.modelimplementations.SecondViewTest@5113ab26: null
com.example.modelimplementations.FirstViewTest@2a64e2d0: null
12:12:45.913 [main] DEBUG org.graphwalker.core.machine.ExecutionContext - Execute method: 'v_FirstView' in model: 'FirstView'
12:12:45.913 [main] DEBUG org.graphwalker.core.machine.ExecutionContext - Execute method: 'e_EnterSecondView' in model: 'FirstView'
12:12:45.914 [main] DEBUG org.graphwalker.core.machine.ExecutionContext - Execute method: 'v_SecondView' in model: 'FirstView'
12:12:45.914 [main] ERROR org.graphwalker.core.machine.SimpleMachine - No path generator is defined
Exception in thread "main" org.graphwalker.core.machine.MachineException: org.graphwalker.core.machine.MachineException: No path generator is defined
at org.graphwalker.core.machine.SimpleMachine.walk(SimpleMachine.java:150)
at org.graphwalker.core.machine.SimpleMachine.getNextStep(SimpleMachine.java:105)
at com.example.runners.GraphWalkerProblemRunner.main(GraphWalkerProblemRunner.java:43)
Caused by: org.graphwalker.core.machine.MachineException: No path generator is defined
at org.graphwalker.core.machine.SimpleMachine.hasNextStep(SimpleMachine.java:273)
at org.graphwalker.core.machine.SimpleMachine.switchContext(SimpleMachine.java:182)
at org.graphwalker.core.machine.SimpleMachine.chooseSharedContext(SimpleMachine.java:204)
at org.graphwalker.core.machine.SimpleMachine.takeNextStep(SimpleMachine.java:193)
at org.graphwalker.core.machine.SimpleMachine.walk(SimpleMachine.java:143)
... 2 more
```

Description with previous problem:
> A minimal working/problematic example to demonstrate an issue with GraphWalker involving programmatically setting a
> path generator and a stop condition in conjunction with a multi-model .json GraphWalker file.

## How to execute
For example in IntelliJ IDEA.
1. Clone the project.
2. Execute `mvn graphwalker:generate-sources`
3. Mark the target/generated-sources/graphwalker directory as a "Sources Root" if it isn't already.
4. Run src/main/java/com/example/runners/GraphWalkerProblemRunner.java.

## Information
_The described goal has been achieved._

The goal is to:
1. Model the System Under Test (SUT) as a single GraphWalker .json file containing multiple models.
2. Run tests on different parts of the SUT by applying different path generators and stop conditions on models. There
   remains the possibility to run tests on the full SUT.

The problem is that I haven't been able to programmatically set path generators and stop conditions on the models.
Initially I came up with a hackish solution that ran but unfortunately had no effect. The idea of the hackish solution
can be seen in [my post](https://groups.google.com/g/graphwalker/c/BD5IsDV2VdI/m/AR1YHpFaAwAJ) to GraphWalker Google
Groups.

The code currently used in this repo (in GraphWalkerProblemRunner.java) is adapted from
[a response](https://groups.google.com/g/graphwalker/c/BD5IsDV2VdI/m/87JIgDzbAwAJ) to my post. I think that this code is
an important step in the right direction. Unfortunately running it results in an error:

```
Exception in thread "main" org.graphwalker.core.condition.StopConditionException: Context missing a model
at org.graphwalker.core.condition.ReachedStopConditionBase.validate(ReachedStopConditionBase.java:55)
at org.graphwalker.core.condition.ReachedVertex.validate(ReachedVertex.java:63)
at org.graphwalker.core.condition.ReachedStopConditionBase.setContext(ReachedStopConditionBase.java:50)
at org.graphwalker.core.generator.PathGeneratorBase.setContext(PathGeneratorBase.java:50)
at org.graphwalker.core.machine.ExecutionContext.setPathGenerator(ExecutionContext.java:138)
at com.example.runners.GraphWalkerProblemRunner.main(GraphWalkerProblemRunner.java:18)
```

The goal of the code in GraphWalkerProblemRunner in this case is to get to the SecondView model as directly as possible
(without the possibility of entering FirstViewSubNode) and then execute SecondView for ten seconds.
