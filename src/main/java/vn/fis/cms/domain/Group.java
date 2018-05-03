package vn.fis.cms.domain;

import vn.fis.cms.model.GroupPermissionModel;

import java.util.List;

public class Group {
    private GroupPermissionModel groupPermissionModel;
    private List<PermissionAuth> permissions;

    public Group(GroupPermissionModel groupPermissionModel, List<PermissionAuth> permissions) {
        this.groupPermissionModel = groupPermissionModel;
        this.permissions = permissions;
    }

    public GroupPermissionModel getGroupPermissionModel() {
        return groupPermissionModel;
    }

    public void setGroupPermissionModel(GroupPermissionModel groupPermissionModel) {
        this.groupPermissionModel = groupPermissionModel;
    }

    public List<PermissionAuth> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionAuth> permissions) {
        this.permissions = permissions;
    }
}
