import annotations.*;
import org.dom4j.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class XmlConverter {
    public static Document XMLFile(Object obj) throws Exception {
        Class clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(XmlObject.class)) {
            throw new Exception("Class hasn't annotation");
        }

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(clazz.getSimpleName().toLowerCase());

        List<Field> fields = new ArrayList<Field>(){{addAll(Arrays.asList(obj.getClass().getDeclaredFields()));}};
        List<Method> methods = new ArrayList<Method>(){{addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));}};
        while (clazz.getSuperclass() != Object.class) {
            clazz = clazz.getSuperclass();
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(XmlAttribute.class)) {
                String tag = field.getAnnotation(XmlAttribute.class).tag();
                String name = field.getAnnotation(XmlAttribute.class).name();
                if (name.equals("")) {
                    name = field.getName();
                }
                if (tag.equals("")) {
                    if (root.attribute(name) != null)
                        throw new Exception("Attribute with name \"" + name + "\" already exist");
                    root.addAttribute(name, field.get(obj).toString());
                    continue;
                }
                if (root.element(tag).attribute(name) != null)
                    throw new Exception("Attribute with name \"" + name + "\" already exist");
                root.element(tag).addAttribute(name, field.get(obj).toString());
            }

            if (field.isAnnotationPresent(XmlTag.class)) {
                if (field.get(obj).getClass().isAnnotationPresent(XmlObject.class)) {
                    Element newElement = XMLFile(field.get(obj)).getRootElement();
                    root.add(newElement);
                    continue;
                }
                String name = field.getAnnotation(XmlTag.class).name();
                if (name.equals("")) {
                    name = field.getName();
                }
                if (root.element(name) != null)
                    throw new Exception("Tag with name \"" + name + "\" already exist");
                root.addElement(name).addText((field.get(obj).toString()));
            }
        }

        for (Method method : methods) {
            if (method.getParameterCount() > 0) {
                throw new Exception("Method have parameters");
            }
            if (method.getGenericReturnType() == void.class) {
                throw new Exception("Method return void");
            }
            method.setAccessible(true);

            if (method.isAnnotationPresent(XmlAttribute.class)) {
                String tag = method.getAnnotation(XmlAttribute.class).tag();
                String name = method.getAnnotation(XmlAttribute.class).name();
                if (name.equals("")) {
                    name = method.getName();
                    if (name.startsWith("get")) {
                        name = name.replaceAll("get", "").toLowerCase();
                    }
                }
                if (tag.equals("")) {
                    if (root.attribute(name) != null)
                        throw new Exception("Attribute with name \"" + name + "\" already exist");
                    root.addAttribute(name, method.invoke(obj).toString());
                    continue;
                }
                if (root.element(tag).attribute(name) != null)
                    throw new Exception("Attribute with name \"" + name + "\" already exist");
                root.element(tag).addAttribute(name, method.invoke(obj).toString());
            }

            if (method.isAnnotationPresent(XmlTag.class)) {
                String name = method.getAnnotation(XmlTag.class).name();
                if (name.equals("")) {
                    name = method.getName();
                    if (name.startsWith("get")) {
                        name = name.replaceAll("get", "").toLowerCase();
                    }
                }
                if (root.element(name) != null)
                    throw new Exception("Tag with name \"" + name + "\" already exist");
                root.addElement(name).addText((method.invoke(obj).toString()));
            }
        }
        return document;
    }
}
