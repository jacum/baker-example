package com.ing.bakerexample

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import com.ing.baker.compiler.RecipeCompiler

object Main extends IOApp {
  def run(args: List[String]) = {

    val compiled = RecipeCompiler.compileRecipe(BakerExample.recipe)
    val visualization: String = compiled.getRecipeVisualization

    println(visualization)

    BakerExampleServer
      .stream[IO]
      .compile
      .drain
      .as(ExitCode.Success)
  }
}