package cn.com.ntesec.uml.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;

import com.google.gson.JsonObject;

import cn.com.ntesec.uml.utils.FileHandler;
import cn.com.ntesec.uml.utils.JsonUtils;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ResourceSet rs = new ResourceSetImpl();
		String resultFilePath = "result.json";
		File umlFile = new File("C:\\Users\\yichli\\uml\\cn.com.ntesec.uml\\cn.com.ntesec.uml\\file\\model.uml");
		URI fileURI = URI.createURI(umlFile.toURI().toString());
		Resource resource = rs.createResource(fileURI);
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.err.println(resource.isLoaded());
		EList<EObject> objects = resource.getContents();
		Map<String, List<PackageableElement>> packageableElement = new HashMap<String, List<PackageableElement>>();
		for(EObject obj : objects){
			if(obj instanceof Model){
				Model model = (Model)obj;
				for(PackageableElement element : model.getPackagedElements()){
					String classStr = element.getClass().toString().split(" ")[1];
					String typeClass = classStr.substring(classStr.lastIndexOf('.')+1);
					if(packageableElement.containsKey(typeClass))
						packageableElement.get(typeClass).add(element);
					else{
						List<PackageableElement> elemlist = new ArrayList<PackageableElement>();
						elemlist.add(element);
						packageableElement.put(typeClass, elemlist);
					}
					//System.out.print(element.getName()+" : ");
					//System.err.println(element.getLabel());
				}
				
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		for (Entry<String, List<PackageableElement>> entry : packageableElement.entrySet()) { 
			String mapKey = entry.getKey();
			List<Object> attrMapList = new ArrayList<Object>();
			
			for(PackageableElement element : entry.getValue()){
				Map<String,Object> attrMap = new HashMap<String,Object>();
				attrMap.put("name",element.getName());
				attrMapList.add(attrMap);
			}
			map.put(mapKey, attrMapList);
			
		}
		
		FileHandler.writeJson(resultFilePath, JsonUtils.mapToJson(map));
		return null;
	}
}
