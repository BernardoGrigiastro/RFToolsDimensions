package mcjty.rftoolsdim.dimension.descriptor;

public class DescriptorError {

    public static DescriptorError ERROR(Code code) {
        return new DescriptorError(code);
    }

    public static DescriptorError ERROR(Code code, String data) {
        return new DescriptorError(code, data);
    }

    public static final DescriptorError OK = new DescriptorError(Code.OK);

    private final Code code;
    private final String data;

    public DescriptorError(Code code, String data) {
        this.code = code;
        this.data = data;
    }

    public DescriptorError(Code code) {
        this.code = code;
        this.data = null;
    }

    public boolean isOk() {
        return this == OK;
    }

    public Code getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        if (data == null) {
            return code.getMessage();
        } else {
            return code.getMessage() + " " + data;
        }
    }

    public enum Code {
        OK(null),
        ONLY_ONE_BIOME_CONTROLLER("You can only have one biome controller!"),
        ONLY_ONE_TERRAIN("You can only have one terrain type!"),
        BAD_BLOCK("Bad block!"),
        BAD_FEATURE("Bad feature!"),
        BAD_TERRAIN_TYPE("Bad terrain type!"),
        BAD_BIOME_CONTROLLER("Bad biome controller!"),
        DANGLING_BLOCKS("Dangling blocks! Blocks should come before either a terrain or a feature");

        private final String message;

        Code(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
