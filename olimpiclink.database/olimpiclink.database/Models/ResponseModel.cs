namespace olimpiclink.database.Models
{
    public class ResponseModel<T>
    {
        public T? Value { get; set; }
        public string Message { get; set; }
        public bool Status { get; set; } = true;
    }
}
