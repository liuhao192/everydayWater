package ren.kura.everydaywater.quartz.exceptions;
/**
 * <p>项目名称: everydaywater
 * <p>文件名称: EveryWaterException.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/23 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/23 9:44
 */
public class EveryWaterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EveryWaterException(String message){
		super(message);
	}
	
	public EveryWaterException(Throwable cause)
	{
		super(cause);
	}
	
	public EveryWaterException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
