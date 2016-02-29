
/**
 * 
 * @param {Object} labelName 首先传入一个标签名，例如li，div，a等
 *  @param {Object} id
 *  @param {Object} class
 * @param {Object} existed_obj  已经存在的标签的对象
 */

function CreateLabelAppentToAnother(labelName, id, classname, existed_obj) {
	var NodeString = "<" + labelName + ">";
	var node_obj = jquery1_4_2(NodeString);

	node_obj.attr("class", classname);
	node_obj.attr("id", id);
	
	node_obj.appendTo(existed_obj);

}




