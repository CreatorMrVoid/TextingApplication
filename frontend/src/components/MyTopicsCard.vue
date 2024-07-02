<script>
async function toMyTopics() {
  try {
    let response = await api.post("/mytopics", {
      headers: {
        "Content-Type": "application/json",
      },
    });
    router.push("/forum/topics/mytopics");
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
