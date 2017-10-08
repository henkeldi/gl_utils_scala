
package org.gl_utils

import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.glfw.GLFWKeyCallback

class Window(
    width: Int,
    height: Int,
    title: String="",
    samples: Int = 1,
    monitor_id: Int = 0,
    fullscreen: Boolean = false
) {
  GLFWErrorCallback.createPrint(System.err).set();
  
  if ( !glfwInit() )
  			throw new IllegalStateException("Unable to initialize GLFW")
  
  glfwWindowHint(GLFW_SAMPLES, samples)

  private val monitors = glfwGetMonitors()
  private val monitor = if (fullscreen) monitors.get(monitor_id) else 0
  
  private val window = glfwCreateWindow(
      width, height, title, monitor, 0
  )
  
  if ( window == -1 )
			throw new RuntimeException("Failed to create the GLFW window");
  
  glfwMakeContextCurrent(window)
  
  glfwSwapInterval(1)
  
  center_window(monitor_id)
  
  GL.createCapabilities();
   
  private var previous_second = glfwGetTime()
  private var frame_count = 0.0
  
  /*
  private def keyHandler (
    window: Long, 
    key: Int, 
    scanCode: Int, 
    action: Int, 
    mods: Int
   ): Unit = 
  {
  	if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
    	glfwSetWindowShouldClose(window, true)
  }
  
  glfwSetKeyCallback(window, keyHandler)
	*/
  
  def center_window(monitor_id: Int) {
    val m = glfwGetMonitors()
    assert(monitor_id >= 0 && monitor_id < m.limit())
    val monitor = m.get(monitor_id)
    val monitor_x_pos = Array[Int](1)
    val monitor_y_pos = Array[Int](1)
    glfwGetMonitorPos(monitor, monitor_x_pos, monitor_y_pos)
    val video_mode = glfwGetVideoMode(monitor)
    val xpos = monitor_x_pos(0) + video_mode.width()/2 - width/2
    val ypos = monitor_y_pos(0) + video_mode.height()/2 - height/2
    glfwSetWindowPos(window, xpos, ypos)
  }
  
  
  def is_open() : Boolean = {
    !glfwWindowShouldClose(this.window)
  }
  
  
  def swap_buffers(){
    glfwSwapBuffers(this.window)
  }
  
  
  def poll_events() {
    glfwPollEvents()
  }
  
  
  def update() {
    swap_buffers
    poll_events
    update_fps_counter
  }
  
  
  def update_fps_counter(){
    val current_second = glfwGetTime()
    val elapsed_seconds = current_second - previous_second
    if ( elapsed_seconds > 0.25 ) {
      previous_second = current_second
      var fps = frame_count.toFloat / elapsed_seconds.toFloat
      glfwSetWindowTitle(window, f"$title @ FPS: $fps%.0f")
      frame_count = 0.0
    }
    frame_count += 1.0
  }
  
  
  def close() {
    glfwTerminate()
  }
  
}