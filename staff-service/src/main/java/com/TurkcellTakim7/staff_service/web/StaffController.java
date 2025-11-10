@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {

    private final CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffHandler;
    private final CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffHandler;
    private final CommandHandler<DeleteStaffCommand, Void> deleteStaffHandler;
    private final QueryHandler<GetStaffQuery, StaffResponse> getStaffHandler;
    private final QueryHandler<GetStaffListQuery, List<StaffResponse>> getStaffListHandler;

    public StaffController(
            CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffHandler,
            CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffHandler,
            QueryHandler<GetStaffQuery, StaffResponse> getStaffHandler,
            QueryHandler<GetStaffListQuery, List<StaffResponse>> getStaffListHandler,
            CommandHandler<DeleteStaffCommand, Void> deleteStaffHandler) {
        this.createStaffHandler = createStaffHandler;
        this.updateStaffHandler = updateStaffHandler;
        this.getStaffHandler = getStaffHandler;
        this.getStaffListHandler = getStaffListHandler;
        this.deleteStaffHandler = deleteStaffHandler;
    }

    @GetMapping("/{id}")
    public StaffResponse getStaff(@PathVariable UUID id) {
        return getStaffHandler.handle(new GetStaffQuery(id));
    }

    @GetMapping
    public List<StaffResponse> getStaffList(GetStaffListQuery query) {
        return getStaffListHandler.handle(query);
    }

    @PostMapping
    public CreatedStaffResponse createStaff(@RequestBody CreateStaffCommand command) {
        return createStaffHandler.handle(command);
    }

    @PutMapping("/{id}")
    public UpdatedStaffResponse updateStaff(@PathVariable UUID id, @RequestBody UpdateStaffRequest request) {
        UpdateStaffCommand command = new UpdateStaffCommand(
                id,
                request.name(),
                request.surname(),
                request.phoneNumber()
        );
        return updateStaffHandler.handle(command);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable UUID id) {
        deleteStaffHandler.handle(new DeleteStaffCommand(id));
    }
}
