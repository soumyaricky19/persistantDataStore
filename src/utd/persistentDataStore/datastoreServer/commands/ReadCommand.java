package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.lang.StringBuilder;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;

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
			String new_line="\n";
			outputStream.write(okMsg.getBytes());
			outputStream.write(Integer.valueOf(new_line));
			outputStream.write(msg.length);
			outputStream.write(Integer.valueOf(new_line));
			outputStream.write(msg);
			outputStream.flush();
		}
	}
}
