
import org.lwjgl.opengl.GL11._
import org.gl_utils.Window

object Main {

  def main(args: Array[String]) {
    
    val win = new Window(600, 500, "Scala kicks ass.")
    
    glClearColor(1.0f, 0.1f, 0.4f, 1.0f)
    
    Program.shader_folder = Main.getClass.getResource("shader").getPath   
    
    val program = new Program(
        "triangle.vs",
        "triangle.frag"
    )
    program.compile_and_use
    
    while(win.is_open) {
      glClear(GL_COLOR_BUFFER_BIT)
      glDrawArrays(GL_TRIANGLES, 0, 3)
      win.update
    }

  }
  
}