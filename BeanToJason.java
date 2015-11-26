	public String toJsonMethod(Object model){
		try{
			JSONArray ja=new JSONArray();
			JSONObject json=new JSONObject();
			JSONObject jo=null;
			Field[] fields = model.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (!f.getName().equalsIgnoreCase("serialVersionUID")) {
					Method m = model.getClass().getMethod(
							"get" + f.getName().substring(0, 1).toUpperCase()
									+ f.getName().substring(1));
					Object value = m.invoke(model);
					jo = new JSONObject();
					jo.put("name", f.getName());
					jo.put("value", value);
					ja.add(jo);
				}
			}
			json.put("data", ja);
			json.put("status", "y");
			json.put("message", "success");
			return json.toString();
		}catch (Exception ex) {
			logger.error(ex);
			return Util.getFailureJson().toString();
		}
		
	}