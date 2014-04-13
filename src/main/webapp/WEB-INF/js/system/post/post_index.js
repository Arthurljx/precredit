Ext.onReady(function() {
	
	Ext.QuickTips.init(); 
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	var grid = CC.create({
		xtype: 'commongrid',
		//region: 'center',
		title: '岗位管理',
		frame: true,
		stripeRows: false,
		autoScroll: true,
	    loadMask: true, 
	    pageSize: pageRecorder,
	    forceFitColumn: false,
	    dataUrl: Ext.getPath() + '/postController/list',
	    checkboxSelModel:sm,
	    columns: [{
	    	columnIndex: 1,
	    	header: '编号', 
	//        	width: 100, 
	    	dataIndex: 'id',
	    	hidden: true
	    },{
	    	columnIndex: 2,
	    	header: '岗位名称', 
	//        	width: 80, 
	    	dataIndex: 'postName'
	    },{
	    	columnIndex: 3,
	    	header: '岗位描述', 
	    	width: 200, 
	    	dataIndex: 'postDesc',
	    	hidden: true
	    },{
	    	xtype: 'custactioncolumn',
	    	header: '修改',
	    	align: 'center',
	    	width: 50,
	    	dataIndex: 'id'
	    }],
	    
	    tbar: [{
	    	text: '添加',
	   	    tooltip: '添加',
	   	    iconCls: 'add',
	   	    handler: function(){
	   	    	
	   	    }
	    },'-',{
	    	text: '批量删除',
	   	    tooltip: '批量删除',
	   	    iconCls: 'delete',
	   	    handler: function(){
	   	    	
	   	    }
	    }],
		
	    plugins:[new Ext.sunyard.PagingToolbarPlugin({
			plugins:[new Ext.sunyard.PagingToolbar.PageCount(),new Ext.ux.ProgressBarPager()]
		})]
	});
	
	new Ext.Viewport({
		layout:'fit',
		items: [grid]
	});
	
});