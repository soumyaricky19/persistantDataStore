package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.lang.StringBuilder;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class ReadCommand extends ServerCommand
{

	@Override
	public void run() throws IOException, ServerException 
	{	
		int ch,i=0;
		byte[] msg;
		StringBuilder sb[]=new StringBuilder[5];
			while((ch=inputStream.read()) != -1) 
			{
				if (i==0)
					continue;
				if ((char)ch !='\n')
					sb[i].append(ch);		
				else
					i++;						
			}
		msg=FileUtil.readData(sb[1].toString());
		if (msg != null)
		{
			String okMsg = "OK";
			StreamUtil.writeLine(okMsg.getBytes()+"\n",outputStream);
			StreamUtil.writeLine(msg.length+"\n",outputStream);
			StreamUtil.writeData(msg,outputStream);
			outputStream.flush();
		}
	}
}
