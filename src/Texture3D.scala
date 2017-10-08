


import org.lwjgl.opengl.GL45._
import java.nio._

class Texture3D(
    levels: Int, 
    internalformat: Int,
    width: Int,
    height: Int,
    depth: Int
) extends ITexture
{
  glTextureStorage3D(id, levels, internalformat, width, height, depth) 


  def sub_image(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int, data_format: Int, data_type: Int, pixels: ByteBuffer) {
    glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, data_format, data_type, pixels)
  }

  
  def sub_image(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int, data_format: Int, data_type: Int, pixels: IntBuffer) {
    glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, data_format, data_type, pixels)
  }

  
  def sub_image(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int, data_format: Int, data_type: Int, pixels: FloatBuffer) {
    glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, data_format, data_type, pixels)
  }
  
  
  def sub_image(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int, data_format: Int, data_type: Int, pixels: DoubleBuffer) {
    glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, data_format, data_type, pixels)
  }

}