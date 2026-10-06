package vn.nvsoft.service.viewmodel;

import vn.nvsoft.service.dto.UserDTO;

public class UserWorkingLocationViewModel {

    private UserDTO user;
    private CheckpointLocationViewModel checkpointLocation;

    public UserWorkingLocationViewModel(UserDTO currentUser, CheckpointLocationViewModel locationInfo) {
        this.user = currentUser;
        this.checkpointLocation = locationInfo;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CheckpointLocationViewModel getCheckpointLocation() {
        return checkpointLocation;
    }

    public void setCheckpointLocation(CheckpointLocationViewModel checkpointLocation) {
        this.checkpointLocation = checkpointLocation;
    }
}
