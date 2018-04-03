inputAS.findAll{
  it.getType() == "RelationClass"
}.each{
  def org = it.getFeatures().get("org-id")
  def pers = it.getFeatures().get("pers-id")
  def type = it.getFeatures().get("rel-type")

  println "$org"

  it.getFeatures().put("org-id", org.toString())
  it.getFeatures().put("pers-id", pers.toString())
}