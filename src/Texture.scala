
import org.lwjgl.opengl.GL45._
import java.nio._

class Texture(
    target: Int, 
    levels: Int, 
    internalformat: Int,
    width: Int,
    height: Int
) extends ITexture
{
  glTextureStorage2D(id, levels, internalformat, width, height)
  

  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: ByteBuffer) {
    glTextureSubImage2D(id, level, xoffset, yoffset, width, height, data_format, data_type, pixels)
  }

  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: IntBuffer) {
    glTextureSubImage2D(id, level, xoffset, yoffset, width, height, data_format, data_type, pixels)
  }

  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: FloatBuffer) {
    glTextureSubImage2D(id, level, xoffset, yoffset, width, height, data_format, data_type, pixels)
  }

  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: DoubleBuffer) {
    glTextureSubImage2D(id, level, xoffset, yoffset, width, height, data_format, data_type, pixels)
  }
  
}




