
import java.io.File

import scala.io.Source

import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL32._
import org.lwjgl.opengl.GL40._
import org.lwjgl.opengl.GL43._

class Program(shader_paths : String*) {
  
  private var program = 0

  def compile() {
    program = glCreateProgram()
    val shaders = new Array[Int](shader_paths.length);
    for((path, i) <- shader_paths.zipWithIndex) {
      var full_path = ""
      if (!Program.shader_folder.equals("")){
          full_path = new File(Program.shader_folder, path).toString()
      } else {
        full_path = path
      }
      val shader = create_shader(full_path)
      glAttachShader(program, shader)
      shaders(i) = shader
    }
    glLinkProgram(program)
    
    val ret = Array[Int](1)
    glGetProgramiv(program, GL_LINK_STATUS, ret)
    
    if(ret(0)==0) {
      val log = glGetProgramInfoLog(program)
      System.err.println(log)
      throw new RuntimeException(
          s"Shader linking failed"
      )
    }
    
    for (shader <- shaders)
      glDeleteShader(shader)
  }

  private def create_shader(path: String) : Int = {
    val suffix = path.substring(path.indexOf(".")+1)
    val shader_type = Program.shader_type_lookup(suffix)
    val shader = glCreateShader(shader_type)
    
    val shader_code = Source.fromFile(path).mkString;
    glShaderSource(shader, shader_code)
    glCompileShader(shader)
    
    val ret = Array[Int](1);
    glGetShaderiv(shader, GL_COMPILE_STATUS, ret)
    if ( ret(0) == 0 ) {
      val log = glGetShaderInfoLog(shader)
      System.err.println(log)
      throw new RuntimeException(
          s"[$path]: Shader compilation failed!"
      )
    }
    shader
  }
  
  def use() {
    glUseProgram(program)
  }
  
  def compile_and_use() {
    compile
    use
  }
  
}

object Program {
  val shader_type_lookup = Map(
      "vs"-> GL_VERTEX_SHADER,
      "tcs"-> GL_TESS_CONTROL_SHADER,
      "tes"-> GL_TESS_EVALUATION_SHADER,
      "gs"-> GL_GEOMETRY_SHADER,
      "frag"-> GL_FRAGMENT_SHADER,
      "cs"-> GL_COMPUTE_SHADER
  )
  var shader_folder = ""
}